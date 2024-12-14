package models

import (
	"database/sql/driver"
	"go-bookstore/pkg/config"
	"gorm.io/gorm"
)

type Role string

const (
	_User Role = "user"
	Admin Role = "admin"
)

type User struct {
	gorm.Model
	Openid string `json:"openid"`
	Phone  string `json:"phone"`
	Role   Role   `json:"role"`
}

func init() {
	config.Connect()

	db := config.GetDB()
	if !db.Migrator().HasTable(&User{}) {
		err := db.AutoMigrate(&User{})
		if err != nil {
			panic("Failed to migrate database schema: " + err.Error())
		}
	}
}

func (u *User) CreateUser() *User {
	err := config.GetDB().Create(&u).Error
	if err != nil {
		panic("Failed to create user: " + err.Error())
	}
	return u
}

func GetAllUsers() []User {
	var users []User
	config.GetDB().Find(&users)
	return users
}

func GetUserById(id uint) (*User, *gorm.DB) {
	var user User
	db := config.GetDB().Where("id = ?", id)
	return &user, db
}

func DeleteUserById(id uint) User {
	var user User
	config.GetDB().Where("id = ?", id).Delete(&user)
	return user
}

func (r *Role) Scan(Value interface{}) error {
	*r = Role(Value.([]byte))
	return nil
}

func (r *Role) Value() (driver.Value, error) {
	return string(*r), nil
}
