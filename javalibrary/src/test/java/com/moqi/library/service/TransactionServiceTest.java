package com.moqi.library.service;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.TransactionDao;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.dao.bo.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private BookService bookService;

    @InjectMocks
    private TransactionService transactionService;

    private Book sampleBook;
    private Transaction sampleTransaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化 Book 和 Transaction 示例对象
        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setStatus("available");

        sampleTransaction = new Transaction();
        sampleTransaction.setId(1L);
        sampleTransaction.setUserId(2L);
        sampleTransaction.setBookId(1L);
        sampleTransaction.setBorrowDate(Date.valueOf(LocalDate.now()));
        sampleTransaction.setReturnDate(Date.valueOf(LocalDate.now().plusMonths(2)));
        sampleTransaction.setStatus("borrowing");
    }

    @Test
    void borrowBook_ShouldSucceed() throws BusinessException {
        // Arrange
        when(bookService.getBookById(1L)).thenReturn(sampleBook);
        when(transactionDao.createTransaction(any(Transaction.class))).thenReturn(sampleTransaction);

        // Act
        Transaction result = transactionService.borrowBook(2L, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("borrowing", result.getStatus());
        verify(bookService, times(1)).updateBook(eq(1L), any(Book.class));
        verify(transactionDao, times(1)).createTransaction(any(Transaction.class));
    }

    @Test
    void borrowBook_WhenBookUnavailable_ShouldThrowException() {
        // Arrange
        sampleBook.setStatus("borrowed");
        when(bookService.getBookById(1L)).thenReturn(sampleBook);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            transactionService.borrowBook(2L, 1L);
        });
        assertEquals(ReturnNo.BOOK_NOT_AVAILABLE, exception.getErrno());
    }

    @Test
    void returnBook_ShouldSucceed() throws BusinessException {
        // Arrange
        when(transactionDao.getTransactionById(1L)).thenReturn(sampleTransaction);
        when(bookService.getBookById(1L)).thenReturn(sampleBook);

        // Act
        transactionService.returnBook(1L);

        // Assert
        verify(transactionDao, times(1)).updateTransaction(any(Transaction.class));
        verify(bookService, times(1)).updateBook(eq(1L), any(Book.class));
    }

    @Test
    void returnBook_WhenTransactionInvalid_ShouldThrowException() {
        // Arrange
        when(transactionDao.getTransactionById(1L)).thenReturn(null);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            transactionService.returnBook(1L);
        });
        assertEquals(ReturnNo.INVALID_TRANSACTION, exception.getErrno());
    }

    @Test
    void renewTransaction_ShouldSucceed() throws BusinessException {
        // Arrange
        when(transactionDao.getTransactionById(1L)).thenReturn(sampleTransaction);

        // Act
        Transaction result = transactionService.renewTransaction(1L);

        // Assert
        assertNotNull(result);
        verify(transactionDao, times(1)).updateTransaction(any(Transaction.class));
    }

    @Test
    void renewTransaction_WhenOverdue_ShouldThrowException() {
        // Arrange
        sampleTransaction.setReturnDate(Date.valueOf(LocalDate.now().minusDays(1)));
        when(transactionDao.getTransactionById(1L)).thenReturn(sampleTransaction);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            transactionService.renewTransaction(1L);
        });
        assertEquals(ReturnNo.TRANSACTION_OVERDUE, exception.getErrno());
    }

    @Test
    void getUnreturnedTransactions_ShouldReturnTransactions() {
        // Arrange
        when(transactionDao.findUnreturnedTransactions(2L)).thenReturn(Arrays.asList(sampleTransaction));

        // Act
        List<Transaction> result = transactionService.getUnreturnedTransactions(2L, false);

        // Assert
        assertEquals(1, result.size());
        verify(transactionDao, times(1)).findUnreturnedTransactions(2L);
    }

    @Test
    void getOverdueTransactions_ShouldReturnTransactions() {
        // Arrange
        when(transactionDao.findOverdueTransactions(any(Date.class), eq(2L)))
                .thenReturn(Arrays.asList(sampleTransaction));

        // Act
        List<Transaction> result = transactionService.getOverdueTransactions(2L, false);

        // Assert
        assertEquals(1, result.size());
        verify(transactionDao, times(1)).findOverdueTransactions(any(Date.class), eq(2L));
    }

    @Test
    void getUpcomingDueTransactions_ShouldReturnTransactions() {
        // Arrange
        when(transactionDao.findTransactionsDueBefore(any(Date.class), any(Date.class)))
                .thenReturn(Arrays.asList(sampleTransaction));

        // Act
        List<Transaction> result = transactionService.getUpcomingDueTransactions();

        // Assert
        assertEquals(1, result.size());
        verify(transactionDao, times(1)).findTransactionsDueBefore(any(Date.class), any(Date.class));
    }
}