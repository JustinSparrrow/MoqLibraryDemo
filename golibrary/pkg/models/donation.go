package models

import "go-bookstore/pkg/config"

type Donation struct {
	AdminId  int64  `gorm:"foreignKey:AdminId;references:id" json:"adminId"`
	BookId   int64  `gorm:"foreignKey:BookId;references:id" json:"bookId"`
	BookDesc string `gorm:"type:text" json:"bookDesc"`

	User User `gorm:"foreignKey:AdminId;references:id" json:"user"`
	Book Book `gorm:"foreignKey:BookId;references:id" json:"book"`
}

func init() {
	config.Connect()

	db := config.GetDB()
	if !db.Migrator().HasTable(&Donation{}) {
		err := db.AutoMigrate(&Donation{})
		if err != nil {
			panic("Failed to migrate database schema: " + err.Error())
		}
	}
}

// CreateDonation 创建捐赠记录
func (d *Donation) CreateDonation() (*Donation, error) {
	err := config.GetDB().Create(&d).Error
	if err != nil {
		return nil, err
	}
	return d, nil
}

// GetAllDonations 获取所有捐赠记录
func GetAllDonations() ([]Donation, error) {
	var donations []Donation
	err := config.GetDB().Preload("User").Preload("Book").Find(&donations).Error
	if err != nil {
		return nil, err
	}
	return donations, nil
}

// GetDonationsByUserId 通过用户ID获取捐赠记录
func GetDonationsByUserId(userId int64) ([]Donation, error) {
	var donations []Donation
	err := config.GetDB().Preload("Book").Where("user_id = ?", userId).Find(&donations).Error
	if err != nil {
		return nil, err
	}
	return donations, nil
}

// GetDonationsByBookId 通过书籍ID获取捐赠记录
func GetDonationsByBookId(bookId int64) ([]Donation, error) {
	var donations []Donation
	err := config.GetDB().Preload("User").Where("book_id = ?", bookId).Find(&donations).Error
	if err != nil {
		return nil, err
	}
	return donations, nil
}

// DeleteDonationById 通过ID删除捐赠记录
func DeleteDonationById(donationId int64) error {
	var donation Donation
	err := config.GetDB().Where("id = ?", donationId).Delete(&donation).Error
	if err != nil {
		return err
	}
	return nil
}
