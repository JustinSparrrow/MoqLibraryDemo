package com.moqi.library.service;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.TransactionDao;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.dao.bo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private TransactionDao transactionDao;
    private BookService bookService;

    @Autowired
    public TransactionService(TransactionDao transactionDao, BookService bookService) {
        this.transactionDao = transactionDao;
        this.bookService = bookService;
    }

    /**
     * 借书操作：用户借阅图书，默认借阅时长为2个月
     *
     * @param userId 用户ID
     * @param bookId 图书ID
     * @return 创建的借阅记录
     * @throws BusinessException 异常处理
     */
    public Transaction borrowBook(Long userId, Long bookId) throws BusinessException {
        Book book = bookService.getBookById(bookId);
        if (!"available".equals(book.getStatus())) {
            throw new BusinessException(ReturnNo.BOOK_NOT_AVAILABLE, "该图书不可借阅");
        }

        // 计算借书的还书日期，默认借阅时长为2个月
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusMonths(2);

        // 创建借书记录
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setBookId(bookId);
        transaction.setBorrowDate(Date.valueOf(borrowDate));
        transaction.setReturnDate(Date.valueOf(returnDate));
        transaction.setStatus("borrowing");

        // 更新图书状态为已借出
        book.setStatus("borrowed");
        bookService.updateBook(bookId, book);

        // 插入借阅记录
        return transactionDao.createTransaction(transaction);
    }

    /**
     * 还书操作：用户归还借阅的图书
     *
     * @param transactionId 借阅记录ID
     * @throws BusinessException 异常处理
     */
    public void returnBook(Long transactionId) throws BusinessException {
        // 获取借阅记录
        Transaction transaction = transactionDao.getTransactionById(transactionId);
        if (transaction == null || !"borrowing".equals(transaction.getStatus())) {
            throw new BusinessException(ReturnNo.INVALID_TRANSACTION, "无效的借阅记录");
        }

        // 更新借阅状态
        transaction.setReturnDate(Date.valueOf(LocalDate.now())); // 实际归还日期
        transaction.setStatus("returned");
        transactionDao.updateTransaction(transaction);

        // 更新图书状态为可借阅
        Book book = bookService.getBookById(transaction.getBookId());
        book.setStatus("available");
        bookService.updateBook(book.getId(), book);
    }

    /**
     * 续借操作：延长当前借阅记录的还书日期2个月
     *
     * @param transactionId 借阅记录ID
     * @return 更新后的借阅记录
     * @throws BusinessException 异常处理
     */
    public Transaction renewTransaction(Long transactionId) throws BusinessException {
        // 获取当前借阅记录
        Transaction transaction = transactionDao.getTransactionById(transactionId);
        if (transaction == null || !"borrowing".equals(transaction.getStatus())) {
            throw new BusinessException(ReturnNo.INVALID_TRANSACTION, "无效的借阅记录");
        }

        // 验证是否已逾期
        LocalDate currentReturnDate = transaction.getReturnDate().toLocalDate();
        if (currentReturnDate.isBefore(LocalDate.now())) {
            throw new BusinessException(ReturnNo.TRANSACTION_OVERDUE, "该借阅记录已逾期，无法续借");
        }

        // 延长还书日期2个月
        LocalDate newReturnDate = currentReturnDate.plusMonths(2);
        transaction.setReturnDate(Date.valueOf(newReturnDate));

        // 更新借阅记录
        transactionDao.updateTransaction(transaction);
        return transaction;
    }

    /**
     * 获取未归还的借阅记录
     *
     * @param userId 用户ID
     * @param isAdmin 是否是管理员
     * @return 未归还的借阅记录列表
     */
    public List<Transaction> getUnreturnedTransactions(Long userId, boolean isAdmin) {
        return isAdmin
                ? transactionDao.findUnreturnedTransactions(null) // 管理员查询所有未归还记录
                : transactionDao.findUnreturnedTransactions(userId); // 普通用户查询自己的未归还记录
    }

    /**
     * 获取逾期的借阅记录
     *
     * @param userId 用户ID
     * @param isAdmin 是否是管理员
     * @return 逾期的借阅记录列表
     */
    public List<Transaction> getOverdueTransactions(Long userId, boolean isAdmin) {
        Date today = Date.valueOf(LocalDate.now());
        return isAdmin
                ? transactionDao.findOverdueTransactions(today, null) // 管理员查询所有逾期记录
                : transactionDao.findOverdueTransactions(today, userId); // 普通用户查询自己的逾期记录
    }

    /**
     * 获取临期提醒的借阅记录（还书日期在未来3天以内）
     *
     * @return 临期借阅记录列表
     */
    public List<Transaction> getUpcomingDueTransactions() {
        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.plusDays(3);
        return transactionDao.findTransactionsDueBefore(Date.valueOf(today), Date.valueOf(thresholdDate));
    }
}
