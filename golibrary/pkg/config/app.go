package config

import (
	"fmt"
	"github.com/joho/godotenv"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	"log"
	"os"
	"sync"
	"time"
)

var ticketCache sync.Map

type TicketCacheEntry struct {
	Ticket    string    `json:"ticket"`
	ExpiresAt time.Time `json:"expires_at"`
}

var db *gorm.DB

func Connect() {
	// 加载 .env 文件
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	// 从环境变量中读取配置
	dbUser := os.Getenv("DB_USER")
	dbPassword := os.Getenv("DB_PASSWORD")
	dbName := os.Getenv("DB_NAME")
	dbHost := os.Getenv("DB_HOST")
	dbPort := os.Getenv("DB_PORT")

	dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local", dbUser, dbPassword, dbHost, dbPort, dbName)
	d, err := gorm.Open(mysql.Open(dsn), &gorm.Config{})
	if err != nil {
		panic(err)
	}
	db = d
}

func GetDB() *gorm.DB {
	return db
}

func GetBookUrl(isbn string) string {
	// 加载 .env 文件
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	url := os.Getenv("BOOKURL")
	apiKey := os.Getenv("APIKEY")

	return fmt.Sprintf("%s?isbn=%s&appKey=%s", url, isbn, apiKey)
}

func GetWXAccessToken() string {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}
	url := os.Getenv("ACCESSTOKEN")
	appid := os.Getenv("APPID")
	appsecret := os.Getenv("APPSECRET")

	return fmt.Sprintf("%sappid=%s&secret=%s", url, appid, appsecret)
}

func GetWXLoginAPI() string {
	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	url := os.Getenv("CODE2SESSION")
	appid := os.Getenv("APPID")
	appsecret := os.Getenv("APPSECRET")

	return fmt.Sprintf("%sappid=%s&secret=%s&js_code=JSCODE&grant_type=authorization_code", url, appid, appsecret)
}
