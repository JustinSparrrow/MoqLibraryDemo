package com.moqi.library.mapper.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.isbn
     *
     * @mbg.generated
     */
    private String isbn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.book_name
     *
     * @mbg.generated
     */
    private String bookName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.author
     *
     * @mbg.generated
     */
    private String author;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.press
     *
     * @mbg.generated
     */
    private String press;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.press_date
     *
     * @mbg.generated
     */
    private LocalDate pressDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.press_place
     *
     * @mbg.generated
     */
    private String pressPlace;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.clc_code
     *
     * @mbg.generated
     */
    private String clcCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.clc_name
     *
     * @mbg.generated
     */
    private String clcName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.binding
     *
     * @mbg.generated
     */
    private String binding;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.language
     *
     * @mbg.generated
     */
    private String language;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.format
     *
     * @mbg.generated
     */
    private String format;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.created_at
     *
     * @mbg.generated
     */
    private LocalDateTime createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Book.updated_at
     *
     * @mbg.generated
     */
    private LocalDateTime updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.id
     *
     * @return the value of Book.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.id
     *
     * @param id the value for Book.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.isbn
     *
     * @return the value of Book.isbn
     *
     * @mbg.generated
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.isbn
     *
     * @param isbn the value for Book.isbn
     *
     * @mbg.generated
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.book_name
     *
     * @return the value of Book.book_name
     *
     * @mbg.generated
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.book_name
     *
     * @param bookName the value for Book.book_name
     *
     * @mbg.generated
     */
    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.author
     *
     * @return the value of Book.author
     *
     * @mbg.generated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.author
     *
     * @param author the value for Book.author
     *
     * @mbg.generated
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.press
     *
     * @return the value of Book.press
     *
     * @mbg.generated
     */
    public String getPress() {
        return press;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.press
     *
     * @param press the value for Book.press
     *
     * @mbg.generated
     */
    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.press_date
     *
     * @return the value of Book.press_date
     *
     * @mbg.generated
     */
    public LocalDate getPressDate() {
        return pressDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.press_date
     *
     * @param pressDate the value for Book.press_date
     *
     * @mbg.generated
     */
    public void setPressDate(LocalDate pressDate) {
        this.pressDate = pressDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.press_place
     *
     * @return the value of Book.press_place
     *
     * @mbg.generated
     */
    public String getPressPlace() {
        return pressPlace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.press_place
     *
     * @param pressPlace the value for Book.press_place
     *
     * @mbg.generated
     */
    public void setPressPlace(String pressPlace) {
        this.pressPlace = pressPlace == null ? null : pressPlace.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.price
     *
     * @return the value of Book.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.price
     *
     * @param price the value for Book.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.clc_code
     *
     * @return the value of Book.clc_code
     *
     * @mbg.generated
     */
    public String getClcCode() {
        return clcCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.clc_code
     *
     * @param clcCode the value for Book.clc_code
     *
     * @mbg.generated
     */
    public void setClcCode(String clcCode) {
        this.clcCode = clcCode == null ? null : clcCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.clc_name
     *
     * @return the value of Book.clc_name
     *
     * @mbg.generated
     */
    public String getClcName() {
        return clcName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.clc_name
     *
     * @param clcName the value for Book.clc_name
     *
     * @mbg.generated
     */
    public void setClcName(String clcName) {
        this.clcName = clcName == null ? null : clcName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.binding
     *
     * @return the value of Book.binding
     *
     * @mbg.generated
     */
    public String getBinding() {
        return binding;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.binding
     *
     * @param binding the value for Book.binding
     *
     * @mbg.generated
     */
    public void setBinding(String binding) {
        this.binding = binding == null ? null : binding.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.language
     *
     * @return the value of Book.language
     *
     * @mbg.generated
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.language
     *
     * @param language the value for Book.language
     *
     * @mbg.generated
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.format
     *
     * @return the value of Book.format
     *
     * @mbg.generated
     */
    public String getFormat() {
        return format;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.format
     *
     * @param format the value for Book.format
     *
     * @mbg.generated
     */
    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.status
     *
     * @return the value of Book.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.status
     *
     * @param status the value for Book.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.created_at
     *
     * @return the value of Book.created_at
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.created_at
     *
     * @param createdAt the value for Book.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Book.updated_at
     *
     * @return the value of Book.updated_at
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Book.updated_at
     *
     * @param updatedAt the value for Book.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}