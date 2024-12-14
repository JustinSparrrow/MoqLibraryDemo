package models

import (
	"database/sql/driver"
	"go-bookstore/pkg/config"
	"gorm.io/gorm"
)

// https://oktools.iokde.com/json2go

type Status string

const (
	Available Status = "available"
	Borrowed  Status = "borrowed"
)

type Book struct {
	gorm.Model
	Isbn       string `json:"isbn"`
	BookName   string `json:"book_name"`
	Author     string `json:"author"`
	Press      string `json:"press"`
	PressDate  string `json:"press_date"`
	PressPlace string `json:"press_place"`
	Price      int    `json:"price"`
	Pictures   string `json:"pictures"`
	ClcCode    string `json:"clc_code"`
	ClcName    string `json:"clc_name"`
	BookDesc   string `json:"book_desc"`
	Binding    string `json:"binding"`
	Language   string `json:"language"`
	Format     string `json:"format"`
	Status     Status `json:"status"`
}

// 连接表
func init() {
	config.Connect()

	db := config.GetDB()
	if !db.Migrator().HasTable(&Book{}) {
		err := db.AutoMigrate(&Book{})
		if err != nil {
			panic("Failed to migrate database schema: " + err.Error())
		}
	}
}

// CreateBook 创建表
// 结构体方法
func (b *Book) CreateBook() *Book {
	err := config.GetDB().Create(&b).Error
	if err != nil {
		panic("Failed to create the book: " + err.Error())
	}
	return b
}

// GetAllBooks 获取所有图书信息
func GetAllBooks() []Book {
	var Books []Book
	config.GetDB().Find(&Books)
	return Books
}

// GetBookById 通过id来获取对应图书
func GetBookById(Id int64) (*Book, *gorm.DB) {
	var getBook Book
	db := config.GetDB().Where("id = ?", Id).Find(&getBook)
	return &getBook, db
}

// DeleteBookById 通过id来删除对应图书
func DeleteBookById(Id int64) Book {
	var book Book
	config.GetDB().Where("id = ?", Id).Delete(book)
	return book
}

// Scan 为 Status 类型实现 Scan 接口（从数据库读取数据时调用）
func (s *Status) Scan(value interface{}) error {
	// 确保 value 是 []byte 类型的数据
	*s = Status(value.([]byte)) // 将数据库中的值转换为 Status 类型
	return nil
}

// Value 为 Status 类型实现 Value 接口（将数据写入数据库时调用）
func (s *Status) Value() (driver.Value, error) {
	return string(*s), nil // 将 Status 类型转换为字符串形式存储到数据库
}
