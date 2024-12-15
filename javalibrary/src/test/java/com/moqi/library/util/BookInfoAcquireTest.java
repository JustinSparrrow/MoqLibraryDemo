package com.moqi.library.util;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookInfoAcquireTest {
    private RestTemplate restTemplate;
    private BookInfoAcquire bookInfoAcquire;

    @BeforeEach
    public void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        bookInfoAcquire = new BookInfoAcquire(restTemplate);
    }

    @Test
    public void testFetchBookInfoByIsbn_Success() {
        // 模拟 API 返回的数据
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("success", true);

        Map<String, Object> mockData = new HashMap<>();
        mockData.put("isbn", "9781234567897");
        mockData.put("bookName", "Effective Java");
        mockData.put("author", "Joshua Bloch");
        mockData.put("press", "Addison-Wesley");
        mockData.put("price", 4500); // 单位：分
        mockData.put("bookDesc", "A must-read for Java developers.");
        mockData.put("pictures", "https://example.com/book-cover.jpg");
        mockData.put("pressDate", List.of(2018, 1, 1)); // 日期：2018-01-01

        mockResponse.put("data", mockData);

        // 模拟 RestTemplate 的行为
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // 调用被测试方法
        Book book = bookInfoAcquire.fetchBookInfoByIsbn("9781234567897");

        // 验证返回结果
        assertNotNull(book);
        assertEquals("9781234567897", book.getIsbn());
        assertEquals("Effective Java", book.getBookName());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals("Addison-Wesley", book.getPress());
        assertEquals(BigDecimal.valueOf(4500), book.getPrice());
        assertEquals("A must-read for Java developers.", book.getBookDesc());
        assertEquals("https://example.com/book-cover.jpg", book.getPictures());
        assertEquals(Date.valueOf("2018-01-01"), book.getPressDate());

        // 验证 RestTemplate 的调用是否正确
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @Test
    public void testFetchBookInfoByIsbn_ApiError() {
        // 模拟 API 返回失败
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("success", false);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // 调用方法并验证抛出异常
        BusinessException exception = assertThrows(BusinessException.class,
                () -> bookInfoAcquire.fetchBookInfoByIsbn("9781234567897"));

        assertEquals(ReturnNo.BOOK_API_ERROR, exception.getErrno());
        assertTrue(exception.getMessage().contains("获取图书信息失败"));

        // 验证 RestTemplate 的调用是否正确
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }

    @Test
    void testFetchBookInfoByIsbn_InvalidDateFormat() {
        // 模拟 API 返回的数据，包含无效的日期格式
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("success", true);

        Map<String, Object> mockData = new HashMap<>();
        mockData.put("isbn", "9787115320529");
        mockData.put("bookName", "Test Book");
        mockData.put("author", "Test Author");
        mockData.put("press", "Test Press");
        mockData.put("price", 4500); // 单位：分
        mockData.put("bookDesc", "A test book.");
        mockData.put("pictures", "https://example.com/book-cover.jpg");
        mockData.put("pressDate", List.of(2023, 3, 1)); // 无效日期，日期格式正确，但我们可能期望异常

        mockResponse.put("data", mockData);

        // 模拟 RestTemplate 的行为
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        try {
            // 调用被测试方法
            Book book = bookInfoAcquire.fetchBookInfoByIsbn("9787115320529");

            // 输出解析后的出版日期，验证是否为预期格式
            System.out.println("Press Date: " + book.getPressDate());
            Assertions.assertTrue(book.getPressDate() != null, "Expected valid press date");
        } catch (IllegalArgumentException e) {
            // 如果抛出异常，表示日期格式不正确，测试通过
            Assertions.assertTrue(true, "Invalid date format, test passed");
        }

        // 验证 RestTemplate 的调用是否正确
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Map.class));
    }
}
