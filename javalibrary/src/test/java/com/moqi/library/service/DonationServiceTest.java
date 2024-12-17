package com.moqi.library.service;

import com.github.pagehelper.PageHelper;
import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.DonationDao;
import com.moqi.library.dao.bo.Donation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationServiceTest {

    @Mock
    private DonationDao donationDao;

    @InjectMocks
    private DonationService donationService;

    private Donation sampleDonation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化测试数据
        sampleDonation = new Donation();
        sampleDonation.setId(1L);
        sampleDonation.setAdminId(2L);
        sampleDonation.setBookId(3L);
        sampleDonation.setStatus(0);
    }

    /**
     * 测试用户捐书功能
     */
    @Test
    void createDonation_ShouldSucceed() {
        // Arrange
        when(donationDao.createDonation(sampleDonation)).thenReturn(sampleDonation);

        // Act
        Donation result = donationService.createDonation(sampleDonation);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(donationDao, times(1)).createDonation(sampleDonation);
    }

    /**
     * 测试用户取消捐赠
     */
    @Test
    void cancelDonation_ShouldCallDao() {
        // Act
        donationService.cancelDonation(1L);

        // Assert
        verify(donationDao, times(1)).deleteDonation(1L);
    }

    /**
     * 测试用户分页查询自己的捐赠记录
     */
    @Test
    void getUserDonations_ShouldReturnDonations() {
        // Arrange
        List<Donation> mockDonations = Arrays.asList(sampleDonation);
        when(donationDao.getDonationsByUserId(2L)).thenReturn(mockDonations);

        // Act
        List<Donation> result = donationService.getUserDonations(2L, 1, 10);

        // Assert
        assertEquals(1, result.size());
        verify(donationDao, times(1)).getDonationsByUserId(2L);
    }

    /**
     * 测试通过ID查询捐赠记录
     */
    @Test
    void getDonationById_ShouldReturnDonation() {
        // Arrange
        when(donationDao.getDonationById(1L)).thenReturn(sampleDonation);

        // Act
        Donation result = donationService.getDonationById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(donationDao, times(1)).getDonationById(1L);
    }

    /**
     * 测试管理员查询某个用户的捐书记录
     */
    @Test
    void getDonationsByUser_ShouldReturnDonations() {
        // Arrange
        List<Donation> mockDonations = Arrays.asList(sampleDonation);
        when(donationDao.getDonationsByUserId(2L)).thenReturn(mockDonations);

        // Act
        List<Donation> result = donationService.getDonationsByUser(2L, 1, 10);

        // Assert
        assertEquals(1, result.size());
        verify(donationDao, times(1)).getDonationsByUserId(2L);
    }

    /**
     * 测试管理员查询某本书的捐赠人
     */
    @Test
    void getDonationsByBook_ShouldReturnDonations() {
        // Arrange
        List<Donation> mockDonations = Arrays.asList(sampleDonation);
        when(donationDao.getDonationsByBookId(3L)).thenReturn(mockDonations);

        // Act
        List<Donation> result = donationService.getDonationsByBook(3L, 1, 10);

        // Assert
        assertEquals(1, result.size());
        verify(donationDao, times(1)).getDonationsByBookId(3L);
    }

    /**
     * 测试获取已验证的捐赠记录（状态为0）
     */
    @Test
    void getValidatedDonation_ShouldSucceed() {
        // Arrange
        when(donationDao.getDonationById(1L)).thenReturn(sampleDonation);

        // Act
        Donation result = donationService.getValidatedDonation(1L);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getStatus());
        verify(donationDao, times(1)).getDonationById(1L);
    }

    /**
     * 测试获取已验证的捐赠记录失败（状态无效）
     */
    @Test
    void getValidatedDonation_WhenStatusInvalid_ShouldThrowException() {
        // Arrange
        Donation invalidDonation = new Donation();
        invalidDonation.setStatus(1); // 状态无效
        when(donationDao.getDonationById(1L)).thenReturn(invalidDonation);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            donationService.getValidatedDonation(1L);
        });
        assertEquals(ReturnNo.DONATION_NOT_FOUND, exception.getErrno());
        verify(donationDao, times(1)).getDonationById(1L);
    }

    /**
     * 测试修改捐赠状态
     */
    @Test
    void updateDonationStatus_ShouldCallDao() {
        // Act
        donationService.updateDonationStatus(1L, 2);

        // Assert
        verify(donationDao, times(1)).updateDonationStatus(1L, 2);
    }
}