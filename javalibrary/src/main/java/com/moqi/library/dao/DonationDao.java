package com.moqi.library.dao;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Donation;
import com.moqi.library.mapper.DonationPoMapper;
import com.moqi.library.mapper.mapper.DonationMapper;
import com.moqi.library.mapper.po.DonationPo;
import com.moqi.library.mapper.po.DonationPoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DonationDao {
    private final static Logger logger = LoggerFactory.getLogger(DonationDao.class);
    private final DonationPoMapper donationPoMapper;
    private final DonationMapper donationMapper;

    @Autowired
    public DonationDao(DonationPoMapper donationPoMapper, DonationMapper donationMapper) {
        this.donationPoMapper = donationPoMapper;
        this.donationMapper = donationMapper;
    }

    /**
     * 创建捐赠记录
     *
     * @param donation 传入的donation对象
     * @return 创建的记录
     * @throws BusinessException 抛出异常
     */
    public Donation createDonation(Donation donation) throws BusinessException {
        DonationPo po = donationMapper.donationToDonationPo(donation);
        donationPoMapper.insertSelective(po);
        return donationMapper.donationPoToDonation(po);
    }

    /**
     * 删除捐赠记录
     *
     * @param id 捐赠id
     */
    public void deleteDonation(Long id) throws BusinessException {
        int ret = donationPoMapper.deleteByPrimaryKey(id);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.DONATION_NOT_FOUND);
        }
    }

    /**
     * 根据id获取捐赠记录
     *
     * @param donationId 捐赠记录id
     * @return Donation对象
     * @throws BusinessException 抛出异常
     */
    public Donation getDonationById(Long donationId) throws BusinessException {
        DonationPo po = donationPoMapper.selectByPrimaryKey(donationId);
        if (po == null) {
            throw new BusinessException(ReturnNo.DONATION_NOT_FOUND, "捐赠不存在");
        }
        return donationMapper.donationPoToDonation(po);
    }

    /**
     * 根据用户id获取捐书记录
     *
     * @param userId 用户id
     * @return 用户捐书记录
     */
    public List<Donation> getDonationsByUserId(Long userId) {
        DonationPoExample example = new DonationPoExample();
        example.createCriteria().andAdminIdEqualTo(userId);
        return donationPoMapper.selectByExample(example).stream()
                .map(donationMapper::donationPoToDonation)
                .collect(Collectors.toList());
    }

    /**
     * 根据书籍ID获取捐赠记录
     *
     * @param bookId 书籍id
     * @return 该种类书捐赠记录
     */
    public List<Donation> getDonationsByBookId(Long bookId) {
        DonationPoExample example = new DonationPoExample();
        example.createCriteria().andBookIdEqualTo(bookId);
        return donationPoMapper.selectByExample(example).stream()
                .map(donationMapper::donationPoToDonation)
                .collect(Collectors.toList());
    }

    /**
     * 更新捐赠图书状态
     *
     * @param donationId 捐赠图书id
     * @param status 状态
     */
    public void updateDonationStatus(Long donationId, Integer status) {
        DonationPo po = donationPoMapper.selectByPrimaryKey(donationId);
        po.setStatus(status);
        donationPoMapper.updateByPrimaryKeySelective(po);
    }
}
