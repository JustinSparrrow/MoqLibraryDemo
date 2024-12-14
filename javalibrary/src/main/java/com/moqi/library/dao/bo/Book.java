package com.moqi.library.dao.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("Book")
public class Book {
    private Long id;               // 图书唯一标识
    private String isbn;           // ISBN号
    private String bookName;       // 书名
    private String author;         // 作者
    private String press;          // 出版社
    private Date pressDate;        // 出版日期
    private String pressPlace;     // 出版地点
    private BigDecimal price;      // 价格
    private String pictures;       // 图片链接（JSON格式）
    private String clcCode;        // 中图分类号
    private String clcName;        // 分类名称
    private String bookDesc;       // 书籍描述
    private String binding;        // 装帧方式
    private String language;       // 语言
    private String format;         // 开本尺寸
    private String status;         // 图书状态：available 或 borrowed
    private Timestamp createdAt;   // 创建时间
    private Timestamp updatedAt;   // 最后更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPressDate() {
        return pressDate;
    }

    public void setPressDate(Date pressDate) {
        this.pressDate = pressDate;
    }

    public String getPressPlace() {
        return pressPlace;
    }

    public void setPressPlace(String pressPlace) {
        this.pressPlace = pressPlace;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getClcCode() {
        return clcCode;
    }

    public void setClcCode(String clcCode) {
        this.clcCode = clcCode;
    }

    public String getClcName() {
        return clcName;
    }

    public void setClcName(String clcName) {
        this.clcName = clcName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
