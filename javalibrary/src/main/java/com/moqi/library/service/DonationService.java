package com.moqi.library.service;

import com.github.pagehelper.PageHelper;
import com.moqi.library.dao.DonationDao;
import com.moqi.library.dao.bo.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    private DonationDao donationDao;

    @Autowired
    public DonationService(DonationDao donationDao) {
        this.donationDao = donationDao;
    }

    /**
     * 用户捐书功能
     *
     * @param donation 捐书对象
     * @return 创建捐赠记录
     */
    public Donation createDonation(Donation donation) {
        return donationDao.createDonation(donation);
    }

    /**
     * 用户取消捐赠
     *
     * @param id 捐赠id
     */
    public void cancelDonation(Long id) {
        donationDao.deleteDonation(id);
    }

    /**
     * 用户查询自己的捐书记录
     *
     * @param userId 用户id
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 捐书记录列表
     */
    public List<Donation> getUserDonations(Long userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return donationDao.getDonationsByUserId(userId);
    }

    /**
     * 管理员查询某个用户的所有捐书记录
     *
     * @param userId 用户id
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 捐书记录列表
     */
    public List<Donation> getDonationsByUser(Long userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return donationDao.getDonationsByUserId(userId);
    }

    /**
     * 管理员查询某本书的捐赠人
     *
     * @param bookId 图书id
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 返回捐赠人列表
     */
    public List<Donation> getDonationsByBook(Long bookId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return donationDao.getDonationsByBookId(bookId);
    }
}