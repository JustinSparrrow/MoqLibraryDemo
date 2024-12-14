package com.moqi.library;

import com.moqi.core.exception.BusinessException;
import com.moqi.library.dao.BookDao;
import com.moqi.library.dao.bo.Book;
import com.moqi.library.mapper.BookPoMapper;
import com.moqi.library.mapper.mapper.BookMapper;
import com.moqi.library.mapper.po.BookPoWithBLOBs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDaoTest {

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookPoMapper bookPoMapper;

    @InjectMocks  // 通过这个注解将 bookDao 注入
    private BookDao bookDao;

    @Test
    public void testCreateBook_Success() throws BusinessException {
        // Arrange
        Book book = new Book();
        book.setBookName("Test Book");
        book.setAuthor("Test Author");

        BookPoWithBLOBs bookPoWithBLOBs = new BookPoWithBLOBs();
        bookPoWithBLOBs.setBookName("Test Book");
        bookPoWithBLOBs.setAuthor("Test Author");

        // 模拟bookMapper.bookToBookPoWithBLOBs方法
        when(bookMapper.bookToBookPoWithBLOBs(book)).thenReturn(bookPoWithBLOBs);
        // 模拟bookPoMapper.insertSelective方法
        when(bookPoMapper.insertSelective(bookPoWithBLOBs)).thenReturn(1);  // 假设插入成功，返回插入的行数
        // 模拟bookMapper.bookPoWithBLOBsToBook方法
        when(bookMapper.bookPoWithBLOBsToBook(bookPoWithBLOBs)).thenReturn(book);

        // Act
        Book result = bookDao.createBook(book);

        // Assert
        assertNotNull(result);
        assertEquals("Test Book", result.getBookName());
        assertEquals("Test Author", result.getAuthor());
    }

    @Test(expected = BusinessException.class)
    public void testCreateBook_DataAccessException() throws BusinessException {
        // Arrange
        Book book = new Book();
        book.setBookName("Test Book");
        book.setAuthor("Test Author");

        BookPoWithBLOBs bookPoWithBLOBs = new BookPoWithBLOBs();
        bookPoWithBLOBs.setBookName("Test Book");
        bookPoWithBLOBs.setAuthor("Test Author");

        // 模拟bookMapper.bookToBookPoWithBLOBs方法
        when(bookMapper.bookToBookPoWithBLOBs(book)).thenReturn(bookPoWithBLOBs);
        // 模拟bookPoMapper.insertSelective方法，抛出异常
        when(bookPoMapper.insertSelective(bookPoWithBLOBs)).thenThrow(new DataAccessException("Database error") {});

        // Act
        bookDao.createBook(book);  // 应该抛出BusinessException

        // Assert: expected exception
    }
}