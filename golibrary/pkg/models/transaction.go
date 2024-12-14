package models

import (
	"go-bookstore/pkg/config"
	"time"
)

type BStatus string

const (
	Borrowing BStatus = "borrowing"
	Returned  BStatus = "returned"
)

type Transaction struct {
	UserId     int64     `gorm:"foreignKey:UserId;references:id" json:"userId"`
	BookId     int64     `gorm:"foreignKey:BookId;references:id" json:"bookId"`
	BorrowDate time.Time `json:"borrow_date"`
	ReturnDate time.Time `json:"return_date"`
	Status     BStatus   `json:"status"`

	User User `gorm:"foreignKey:UserId;references:id" json:"user"`
	Book Book `gorm:"foreignKey:BookId;references:id" json:"book"`
}

func init() {
	config.Connect()

	db := config.GetDB()
	if !db.Migrator().HasTable(&Transaction{}) {
		err := db.AutoMigrate(&Transaction{})
		if err != nil {
			panic("Failed to migrate database schema: " + err.Error())
		}
	}
}

// CreateTransaction 创建一条新的交易记录
func CreateTransaction(transaction *Transaction) (*Transaction, error) {
	err := config.GetDB().Create(&transaction).Error
	if err != nil {
		return nil, err
	}
	return transaction, nil
}

// GetAllTransactions 获取所有的交易记录
func GetAllTransactions() ([]Transaction, error) {
	var transactions []Transaction
	err := config.GetDB().Preload("User").Preload("Book").Find(&transactions).Error
	if err != nil {
		return nil, err
	}
	return transactions, nil
}

// GetTransactionById 根据ID获取某条交易记录
func GetTransactionById(id int64) (*Transaction, error) {
	var transaction Transaction
	err := config.GetDB().Preload("User").Preload("Book").Where("id = ?", id).First(&transaction).Error
	if err != nil {
		return nil, err
	}
	return &transaction, nil
}

// GetTransactionsByUserId 根据用户ID获取交易记录
func GetTransactionsByUserId(userId int64) ([]Transaction, error) {
	var transactions []Transaction
	err := config.GetDB().Preload("User").Preload("Book").Where("user_id = ?", userId).Find(&transactions).Error
	if err != nil {
		return nil, err
	}
	return transactions, nil
}

// DeleteTransactionById 根据ID删除交易记录
func DeleteTransactionById(id int64) error {
	err := config.GetDB().Where("id = ?", id).Delete(&Transaction{}).Error
	if err != nil {
		return err
	}
	return nil
}
