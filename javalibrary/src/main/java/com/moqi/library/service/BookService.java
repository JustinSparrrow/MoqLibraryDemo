package com.moqi.library.service;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.BookDao;
import com.moqi.library.dao.DonationDao;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.dao.bo.Donation;
import com.moqi.library.util.BookInfoAcquire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BookService {
    private Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookDao bookDao;
    private DonationDao donationDao;

    private BookInfoAcquire bookInfoAcquire;

    @Autowired
    public BookService(BookDao bookDao, DonationDao donationDao, BookInfoAcquire bookInfoAcquire) {
        this.bookDao = bookDao;
        this.donationDao = donationDao;
        this.bookInfoAcquire = bookInfoAcquire;
    }

    /**
     * 审核通过并上架图书
     *
     * @param donationId 捐赠记录ID
     * @return 上架后的图书信息
     * @throws BusinessException 抛出异常
     */
    public Book approveAndAddBook(Long donationId) throws BusinessException {
        Donation donation = donationDao.getDonationById(donationId);
        if (donation == null || !donation.getStatus().equals("0")) {
            throw new BusinessException(ReturnNo.DONATION_NOT_FOUND, "捐赠记录不存在或状态无效");
        }
        Book bookInfo = bookInfoAcquire.fetchBookInfoByIsbn(donation.getIsbn());
        if (bookInfo == null) {
            throw new BusinessException(ReturnNo.BOOK_API_ERROR, "通过ISBN获取图书信息失败");
        }
        bookInfo.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Book addedBook = bookDao.createBook(bookInfo);
        donationDao.updateDonationStatus(donationId, 1);
        return addedBook;
    }

    /**
     * 审核并拒绝上架图书
     *
     * @param donationId 捐赠记录id
     * @throws BusinessException 抛出异常
     */
    public void rejectDonation(Long donationId) throws BusinessException {
        Donation donation = donationDao.getDonationById(donationId);
        if (donation == null || !donation.getStatus().equals("0")) {
            throw new BusinessException(ReturnNo.DONATION_NOT_FOUND, "捐赠记录不存在或状态无效");
        }
        donationDao.updateDonationStatus(donationId, 2);
    }
}
