package com.moqi.library.service;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.BookDao;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.dao.bo.Donation;
import com.moqi.library.util.BookInfoAcquire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    private BookDao bookDao;

    @Mock
    private DonationService donationService;

    @Mock
    private BookInfoAcquire bookInfoAcquire;

    @InjectMocks
    private BookService bookService;

    private Book sampleBook;
    private Donation sampleDonation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化测试数据
        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setBookName("Test Book");
        sampleBook.setAuthor("Author A");
        sampleBook.setPress("Test Press");
        sampleBook.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        sampleDonation = new Donation();
        sampleDonation.setId(1L);
        sampleDonation.setIsbn("1234567890");
        sampleDonation.setStatus(0);
    }

    @Test
    void approveAndAddBook_ShouldSucceed() throws BusinessException {
        // Arrange
        when(donationService.getValidatedDonation(1L)).thenReturn(sampleDonation);
        when(bookInfoAcquire.fetchBookInfoByIsbn("1234567890")).thenReturn(sampleBook);
        when(bookDao.createBook(any(Book.class))).thenReturn(sampleBook);

        // Act
        Book result = bookService.approveAndAddBook(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Book", result.getBookName());
        verify(donationService).updateDonationStatus(1L, 1); // 验证更新状态
        verify(bookDao).createBook(any(Book.class));         // 验证DAO调用
    }

    @Test
    void approveAndAddBook_WhenBookInfoNotFound_ShouldThrowException() throws BusinessException {
        // Arrange
        when(donationService.getValidatedDonation(1L)).thenReturn(sampleDonation);
        when(bookInfoAcquire.fetchBookInfoByIsbn("1234567890")).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            bookService.approveAndAddBook(1L);
        });
        assertEquals(ReturnNo.BOOK_API_ERROR, exception.getErrno());
    }

    @Test
    void rejectDonation_ShouldSucceed() throws BusinessException {
        // Arrange
        when(donationService.getDonationById(1L)).thenReturn(sampleDonation);

        // Act
        bookService.rejectDonation(1L);

        // Assert
        verify(donationService).updateDonationStatus(1L, 2);
    }

    @Test
    void deleteBook_ShouldCallDao() throws BusinessException {
        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookDao).deleteBook(1L);
    }

    @Test
    void allBooksInfo_ShouldReturnBookList() throws BusinessException {
        // Arrange
        when(bookDao.getAllBooks()).thenReturn(Arrays.asList(sampleBook));

        // Act
        List<Book> result = bookService.allBooksInfo();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getBookName());
    }

    @Test
    void getSameBooksInfo_ShouldReturnMatchingBooks() throws BusinessException {
        // Arrange
        when(bookDao.getBooksByName("Test Book")).thenReturn(Arrays.asList(sampleBook));

        // Act
        List<Book> result = bookService.getSameBooksInfo("Test Book");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getBookName());
    }

    @Test
    void updateBook_ShouldSucceed() throws BusinessException {
        // Arrange
        Book newBookData = new Book();
        newBookData.setBookName("Updated Book");
        newBookData.setAuthor("Updated Author");

        when(bookDao.getBookById(1L)).thenReturn(sampleBook);
        when(bookDao.modifyBook(any(Book.class))).thenReturn(newBookData);

        // Act
        Book result = bookService.updateBook(1L, newBookData);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Book", result.getBookName());
        assertEquals("Updated Author", result.getAuthor());
        verify(bookDao).modifyBook(any(Book.class));
    }
}
