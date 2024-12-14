package services

import (
	"errors"
	"go-bookstore/pkg/config"
	"go-bookstore/pkg/models"
)

// BorrowBook 借书
func BorrowBook(isbn string) (*models.Book, error) {
	// 创建书籍实例
	book := &models.Book{}
	// 查询书籍是否存在
	db := config.GetDB()
	err := db.Where("isbn = ?", isbn).First(book).Error
	if err != nil {
		return nil, errors.New("book not found")
	}
	// 检查书籍状态
	if book.Status == models.Borrowed {
		return nil, errors.New("book already borrowed")
	}
	// 更新书籍状态
	book.Status = models.Borrowed
	db.Save(book)

	return book, nil
}

// ReturnBook 还书
func ReturnBook(isbn string) (*models.Book, error) {
	book := &models.Book{}
	db := config.GetDB()
	err := db.Where("isbn = ?", isbn).First(book).Error
	if err != nil {
		return nil, errors.New("book not found")
	}
	if book.Status == models.Available {
		return nil, errors.New("book is not borrowed")
	}
	book.Status = models.Borrowed
	db.Save(book)

	return book, nil
}

// DeleteBook 删除书
func DeleteBook(isbn string) error {
	book := &models.Book{}
	db := config.GetDB()
	err := db.Where("isbn = ?", isbn).Delete(book).Error
	if err != nil {
		return errors.New("book not found")
	}
	return nil
}
