package models

import (
	"go-bookstore/pkg/config"
	"gorm.io/gorm"
)

type Review struct {
	gorm.Model
	UserId  int64  `gorm:"foreignKey:UserId;references:id" json:"user_id"` // 外键字段，关联 User 表的 ID
	BookId  int64  `gorm:"foreignKey:BookId;references:id" json:"book_id"` // 外键字段，关联 Book 表的 ID
	Content string `json:"content"`
	Rating  int    `json:"rating"`

	User User `gorm:"foreignKey:UserId;references:id" json:"user"` // 关联的 User 数据
	Book Book `gorm:"foreignKey:BookId;references:id" json:"book"` // 关联的 Book 数据
}

func init() {
	config.Connect()

	db := config.GetDB()
	if !db.Migrator().HasTable(&Review{}) {
		err := db.AutoMigrate(&Review{})
		if err != nil {
			panic("Failed to migrate database schema: " + err.Error())
		}
	}
}

func CreateReview(review *Review) (*Review, error) {
	err := config.GetDB().Create(&review).Error
	if err != nil {
		return nil, err
	}
	return review, nil
}

// GetReviewById 根据评论 ID 获取评论
func GetReviewById(id int64) (*Review, error) {
	var review Review
	err := config.GetDB().Preload("User").Preload("Book").Where("id = ?", id).First(&review).Error
	if err != nil {
		return nil, err
	}
	return &review, nil
}

func GetAllReviews() ([]Review, error) {
	var reviews []Review
	err := config.GetDB().Preload("User").Preload("Book").Find(&reviews).Error
	if err != nil {
		return nil, err
	}
	return reviews, nil
}

// DeleteReviewById 根据评论 ID 删除评论
func DeleteReviewById(id int64) error {
	var review Review
	err := config.GetDB().Where("id = ?", id).Delete(&review).Error
	if err != nil {
		return err
	}
	return nil
}
