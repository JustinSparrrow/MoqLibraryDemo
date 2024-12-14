package services

import (
	"errors"
	"go-bookstore/pkg/config"
	"go-bookstore/pkg/models"
	"go-bookstore/pkg/utils"
)

func CreateDonation(adminId int64, isbn string, bookDesc string) (*models.Donation, error) {
	db := config.GetDB()
	book := &models.Book{}
	if err := db.Where("isbn = ?", isbn).First(book).Error; err == nil {
		return nil, errors.New("book already exists")
	}
	donation := &models.Donation{
		AdminId:  adminId,
		BookId:   0,
		BookDesc: bookDesc,
	}
	if err := db.Create(donation).Error; err != nil {
		return nil, errors.New("fail to create donation")
	}
	return donation, nil
}

func ApproveDonation(donationId int64) (*models.Book, error) {
	db := config.GetDB()
	donation := &models.Donation{}
	if err := db.First(donation, donationId).Error; err != nil {
		return nil, errors.New("fail to find donation")
	}
	if donation.BookId != 0 {
		return nil, errors.New("donation is already approved")
	}
	// 获取书籍的 ISBN
	bookRecord := &models.Book{}
	if err := db.First(bookRecord, donation.BookId).Error; err != nil {
		return nil, errors.New("book record not found")
	}
	// 使用 ISBN 获取书籍信息
	book := &models.Book{}
	err := utils.FetchBookInfo(bookRecord.Isbn, book) // 使用 ISBN 而不是 BookId
	if err != nil {
		return nil, errors.New("failed to fetch book information")
	}
	// 添加书籍到系统
	book.Status = models.Available
	if err := db.Create(book).Error; err != nil {
		return nil, errors.New("failed to add book to system")
	}
	// 更新 Donation 记录，绑定 BookId
	donation.BookId = int64(book.ID)
	if err := db.Save(donation).Error; err != nil {
		return nil, errors.New("failed to update donation record")
	}
	return book, nil
}

// GetAllDonations 获取所有捐书记录
func GetAllDonations() ([]models.Donation, error) {
	db := config.GetDB()

	var donations []models.Donation
	if err := db.Preload("User").Preload("Book").Find(&donations).Error; err != nil {
		return nil, errors.New("failed to fetch donations")
	}

	return donations, nil
}

// DeleteDonation 删除捐书记录
func DeleteDonation(donationId int64) error {
	db := config.GetDB()

	if err := db.Delete(&models.Donation{}, donationId).Error; err != nil {
		return errors.New("failed to delete donation record")
	}

	return nil
}
