package utils

import (
	"encoding/json"
	"errors"
	"go-bookstore/pkg/config"
	"go-bookstore/pkg/models"
	"net/http"
)

// FetchBookInfo 获取图书信息
// 访问 url 获得相应，用 GET 方法
func FetchBookInfo(isbn string, book *models.Book) error {
	url := config.GetBookUrl(isbn)
	resp, err := http.Get(url)
	if err != nil {
		return errors.New("Error fetching book info: " + err.Error())
	}
	defer resp.Body.Close()

	// 定义一个临时结构体来接收响应数据
	var response struct {
		Code    int          `json:"code"`
		Message string       `json:"msg"`
		Data    *models.Book `json:"data"` // 只需要 `data` 字段
		Success bool         `json:"success"`
	}

	// 如果请求成功，解析返回的 JSON 数据
	if resp.StatusCode == http.StatusOK {
		err := json.NewDecoder(resp.Body).Decode(&response)
		if err != nil {
			return errors.New("failed to decode response body")
		}

		// 如果成功，提取 data 部分
		if response.Success && response.Data != nil {
			// 将返回的 data 部分填充到传入的 book 对象
			*book = *response.Data
			return nil
		} else {
			return errors.New("failed to get valid book data from API")
		}
	} else {
		return errors.New("failed to fetch book data, status: " + resp.Status)
	}
}
