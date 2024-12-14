package com.moqi.library.mapper.po;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.book_id
     *
     * @mbg.generated
     */
    private Long bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.borrow_date
     *
     * @mbg.generated
     */
    private LocalDate borrowDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.return_date
     *
     * @mbg.generated
     */
    private LocalDate returnDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.created_at
     *
     * @mbg.generated
     */
    private LocalDateTime createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Transaction.updated_at
     *
     * @mbg.generated
     */
    private LocalDateTime updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.id
     *
     * @return the value of Transaction.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.id
     *
     * @param id the value for Transaction.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.user_id
     *
     * @return the value of Transaction.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.user_id
     *
     * @param userId the value for Transaction.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.book_id
     *
     * @return the value of Transaction.book_id
     *
     * @mbg.generated
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.book_id
     *
     * @param bookId the value for Transaction.book_id
     *
     * @mbg.generated
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.borrow_date
     *
     * @return the value of Transaction.borrow_date
     *
     * @mbg.generated
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.borrow_date
     *
     * @param borrowDate the value for Transaction.borrow_date
     *
     * @mbg.generated
     */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.return_date
     *
     * @return the value of Transaction.return_date
     *
     * @mbg.generated
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.return_date
     *
     * @param returnDate the value for Transaction.return_date
     *
     * @mbg.generated
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.status
     *
     * @return the value of Transaction.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.status
     *
     * @param status the value for Transaction.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.created_at
     *
     * @return the value of Transaction.created_at
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.created_at
     *
     * @param createdAt the value for Transaction.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Transaction.updated_at
     *
     * @return the value of Transaction.updated_at
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Transaction.updated_at
     *
     * @param updatedAt the value for Transaction.updated_at
     *
     * @mbg.generated
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}