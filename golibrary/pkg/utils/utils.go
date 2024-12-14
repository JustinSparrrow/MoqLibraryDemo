package utils

import (
	"encoding/json"
	"io"
	"net/http"
)

// ParseBody 解析 HTTP 请求体并将其绑定到指定的结构体
// @brief:
//   - r: *http.Request，HTTP 请求对象
//   - x: interface{}，用于接收解析结果的目标结构体的指针
//
// @func:
//   - 从 HTTP 请求的 Body 中读取内容，并将其解析为 JSON。
//   - 将解析后的结果绑定到 `x` 所指向的目标结构体。
//   - 如果解析过程中发生错误，Body 内容将被忽略，并返回空的目标结构体。
//
// @attention:
//   - `x` 必须是结构体的指针，否则解析结果不会生效。
func ParseBody(r *http.Request, x interface{}) error {
	// 尝试读取 HTTP 请求体内容
	body, err := io.ReadAll(r.Body)
	if err != nil {
		return err // 返回读取错误
	}
	// 尝试将读取到的 JSON 内容解析到目标结构体
	err = json.Unmarshal(body, x)
	if err != nil {
		return err // 返回解析错误
	}
	return nil // 成功解析返回 nil
}
