package com.moqi.library.dao;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Transaction;
import com.moqi.library.mapper.TransactionPoMapper;
import com.moqi.library.mapper.mapper.TransactionMapper;
import com.moqi.library.mapper.po.TransactionPo;
import com.moqi.library.mapper.po.TransactionPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionDao {
    private final TransactionPoMapper transactionPoMapper;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionDao(TransactionPoMapper transactionPoMapper, TransactionMapper transactionMapper) {
        this.transactionPoMapper = transactionPoMapper;
        this.transactionMapper = transactionMapper;
    }

    /**
     * 创建借书记录
     *
     * @param transaction 传入的transaction对象
     * @return 创建的记录
     * @throws BusinessException 抛出异常
     */
    public Transaction createTransaction(Transaction transaction) throws BusinessException {
        TransactionPo po = transactionMapper.transactionToTransactionPo(transaction);
        transactionPoMapper.insertSelective(po);
        return transactionMapper.transactionPoTotransaction(po);
    }

    /**
     * 删除借书记录
     *
     * @param id 记录id
     */
    public void deleteTransaction(Long id) throws BusinessException {
        int ret = transactionPoMapper.deleteByPrimaryKey(id);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BORROW_RECORD_FAILED);
        }
    }

    /**
     * 根据id获取借书记录
     *
     * @param id 借书记录id
     * @return transaction对象
     */
    public Transaction getTransactionById(Long id) throws BusinessException {
        TransactionPo po = transactionPoMapper.selectByPrimaryKey(id);
        if (po == null) {
            throw new BusinessException(ReturnNo.BORROW_RECORD_FAILED, "借阅记录不存在");
        }
        return transactionMapper.transactionPoTotransaction(po);
    }

    /**
     * 更改借阅记录状态
     *
     * @param transaction 新的transaction对象
     * @return transaction
     */
    public Transaction updateTransaction(Transaction transaction) throws BusinessException {
        TransactionPo po = transactionMapper.transactionToTransactionPo(transaction);
        int ret = transactionPoMapper.updateByPrimaryKeySelective(po);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BORROW_RECORD_FAILED, "借阅记录更新失败");
        }
        return transactionMapper.transactionPoTotransaction(po);
    }

    /**
     * 查询未归还的借阅记录（按用户ID筛选）
     *
     * @param userId 用户ID（可选，为null时查询所有用户）
     * @return 未归还的借阅记录列表
     */
    public List<Transaction> findUnreturnedTransactions(Long userId) {
        TransactionPoExample example = new TransactionPoExample();
        TransactionPoExample.Criteria criteria = example.createCriteria();

        criteria.andStatusEqualTo("borrowing"); // 未归还状态

        if (userId != null) {
            criteria.andUserIdEqualTo(userId); // 如果有用户ID，按用户ID筛选
        }

        return transactionPoMapper.selectByExample(example).stream()
                .map(transactionMapper::transactionPoTotransaction)
                .collect(Collectors.toList());
    }


    /**
     * 查询临期图书列表
     *
     * @param borrowData 开始借阅时间
     * @param returnDate 借阅期限
     * @return 图书列表
     */
    public List<Transaction> findTransactionsDueBefore(Date borrowData, Date returnDate) {
        TransactionPoExample example = new TransactionPoExample();
        example.createCriteria().andReturnDateBetween(borrowData.toLocalDate(), returnDate.toLocalDate());
        return transactionPoMapper.selectByExample(example).stream()
                .map(transactionMapper::transactionPoTotransaction)
                .collect(Collectors.toList());
    }

    /**
     * 查询逾期的借阅记录（按用户ID筛选）
     *
     * @param today 当前日期
     * @param userId 用户ID（可选，为null时查询所有用户）
     * @return 逾期的借阅记录列表
     */
    public List<Transaction> findOverdueTransactions(Date today, Long userId) {
        TransactionPoExample example = new TransactionPoExample();
        TransactionPoExample.Criteria criteria = example.createCriteria();

        criteria.andReturnDateLessThan(today.toLocalDate()); // 还书日期早于当前日期
        criteria.andStatusEqualTo("borrowing");              // 状态为借阅中

        if (userId != null) {
            criteria.andUserIdEqualTo(userId); // 如果有用户ID，按用户ID筛选
        }

        return transactionPoMapper.selectByExample(example).stream()
                .map(transactionMapper::transactionPoTotransaction)
                .collect(Collectors.toList());
    }

    /**
     * 续借图书
     *
     * @param transactionId 借阅记录ID
     * @param newReturnDate 新的还书日期
     * @throws BusinessException 续借失败时抛出异常
     */
    public void renewTransaction(Long transactionId, Date newReturnDate) throws BusinessException {
        TransactionPo transactionPo = transactionPoMapper.selectByPrimaryKey(transactionId);
        if (transactionPo == null || !"borrowing".equals(transactionPo.getStatus())) {
            throw new BusinessException(ReturnNo.BORROW_RECORD_FAILED, "无效的续借操作，记录不存在或已完成借阅");
        }

        transactionPo.setReturnDate(newReturnDate.toLocalDate());
        int ret = transactionPoMapper.updateByPrimaryKeySelective(transactionPo);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.BORROW_RECORD_FAILED, "续借操作失败");
        }
    }
}
