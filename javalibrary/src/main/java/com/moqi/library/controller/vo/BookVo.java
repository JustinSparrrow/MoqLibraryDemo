package com.moqi.library.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVo {
    private String isbn;           // ISBN号
    private String bookName;       // 书名
    private String author;         // 作者
    private String press;          // 出版社
    private String pressDate;      // 出版日期（格式化为 String，如 "2024-12-12"）
    private String price;          // 价格（格式化为带货币单位的字符串，如 "￥45.00"）
    private String pictures;       // 图片链接数组（从 JSON 格式解析后得到的列表）
    private String clcName;        // 分类名称
    private String bookDesc;       // 书籍描述
    private String status;         // 图书状态（比如 `可借阅` 或 `已借出`，根据状态翻译为前端可读文案）
}
