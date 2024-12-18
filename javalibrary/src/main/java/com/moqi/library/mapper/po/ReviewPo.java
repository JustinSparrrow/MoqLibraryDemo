package com.moqi.library.mapper.po;

import java.time.LocalDateTime;

public class ReviewPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.book_id
     *
     * @mbg.generated
     */
    private Long bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.rating
     *
     * @mbg.generated
     */
    private Integer rating;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.created_at
     *
     * @mbg.generated
     */
    private LocalDateTime createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Review.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.id
     *
     * @return the value of Review.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.id
     *
     * @param id the value for Review.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.user_id
     *
     * @return the value of Review.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.user_id
     *
     * @param userId the value for Review.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.book_id
     *
     * @return the value of Review.book_id
     *
     * @mbg.generated
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.book_id
     *
     * @param bookId the value for Review.book_id
     *
     * @mbg.generated
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.rating
     *
     * @return the value of Review.rating
     *
     * @mbg.generated
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.rating
     *
     * @param rating the value for Review.rating
     *
     * @mbg.generated
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.created_at
     *
     * @return the value of Review.created_at
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.created_at
     *
     * @param createdAt the value for Review.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Review.content
     *
     * @return the value of Review.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Review.content
     *
     * @param content the value for Review.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}