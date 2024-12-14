package com.moqi.library.util;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.dao.bo.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class BookInfoAcquire {
    private static final String API_URL = "http://data.isbn.work/openApi/getInfoByIsbn";
    private static final String API_KEY = "ae1718d4587744b0b79f940fbef69e77";

    private final RestTemplate restTemplate;

    public BookInfoAcquire(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Book fetchBookInfoByIsbn(String isbn) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("appKey", API_KEY)
                .queryParam("isbn", isbn)
                .toUriString();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !Boolean.TRUE.equals(response.get("success"))) {
            throw new BusinessException(ReturnNo.BOOK_API_ERROR, "获取图书信息失败：" + response);
        }
        Map<String, Object> data = (Map<String, Object>) response.get("data");

        if (data == null) {
            throw new BusinessException(ReturnNo.BOOK_API_ERROR, "API 数据为空");
        }

        // 构造 Book 对象
        Book book = new Book();
        book.setIsbn((String) data.get("isbn"));
        book.setBookName((String) data.get("bookName"));
        book.setAuthor((String) data.get("author"));
        book.setPress((String) data.get("press"));
        book.setPrice(BigDecimal.valueOf((Integer) data.get("price")));
        book.setBookDesc((String) data.get("bookDesc"));
        book.setPictures(data.get("pictures") != null ? data.get("pictures").toString() : null);

        // 解析出版日期
        Object pressDate = data.get("pressDate");
        if (pressDate instanceof List<?>) { // 检查是否为 List 类型
            @SuppressWarnings("unchecked")
            List<Integer> dateList = (List<Integer>) pressDate; // 强制转换为 List<Integer>
            if (dateList.size() >= 3) { // 确保有年份、月份和日期
                int year = dateList.get(0);
                int month = dateList.get(1);
                int day = dateList.get(2);

                // 格式化为 yyyy-MM-dd 格式
                String date = String.format("%04d-%02d-%02d", year, month, day);

                // 转换为 java.sql.Date 类型并设置
                book.setPressDate(Date.valueOf(date));
            } else {
                throw new IllegalArgumentException("出版日期数据格式不正确，必须包含年、月、日");
            }
        } else {
            throw new IllegalArgumentException("出版日期格式错误，必须为 JSON 数组");
        }

        return book;
    }
}
