package com.moqi.library.mapper.po;

import java.time.LocalDateTime;

public class DonationPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.admin_id
     *
     * @mbg.generated
     */
    private Long adminId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.book_id
     *
     * @mbg.generated
     */
    private Long bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.created_at
     *
     * @mbg.generated
     */
    private LocalDateTime createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.isbn
     *
     * @mbg.generated
     */
    private String isbn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Donation.book_desc
     *
     * @mbg.generated
     */
    private String bookDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.id
     *
     * @return the value of Donation.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.id
     *
     * @param id the value for Donation.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.admin_id
     *
     * @return the value of Donation.admin_id
     *
     * @mbg.generated
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.admin_id
     *
     * @param adminId the value for Donation.admin_id
     *
     * @mbg.generated
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.book_id
     *
     * @return the value of Donation.book_id
     *
     * @mbg.generated
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.book_id
     *
     * @param bookId the value for Donation.book_id
     *
     * @mbg.generated
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.created_at
     *
     * @return the value of Donation.created_at
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.created_at
     *
     * @param createdAt the value for Donation.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.status
     *
     * @return the value of Donation.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.status
     *
     * @param status the value for Donation.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.isbn
     *
     * @return the value of Donation.isbn
     *
     * @mbg.generated
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.isbn
     *
     * @param isbn the value for Donation.isbn
     *
     * @mbg.generated
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Donation.book_desc
     *
     * @return the value of Donation.book_desc
     *
     * @mbg.generated
     */
    public String getBookDesc() {
        return bookDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Donation.book_desc
     *
     * @param bookDesc the value for Donation.book_desc
     *
     * @mbg.generated
     */
    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc == null ? null : bookDesc.trim();
    }
}