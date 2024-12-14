package utils

import (
	"encoding/json"
	"go-bookstore/pkg/config"
	"io/ioutil"
	"net/http"
)

// 微信 js-jdk 使用
// https://juejin.cn/post/6844903651366928391

// 微信登录教程
//https://www.cnblogs.com/waller/p/11802012.html

// 获取微信AccessToken，使得前端可以使用 js-sdk
func getAccessToken() (string, error) {
	resp, err := http.Get(config.GetWXAccessToken())
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}
	var result struct {
		AccessToken string `json:"access_token"`
		ExpiresIn   int    `json:"expires_in"`
	}
	err = json.Unmarshal(body, &result)
	if err != nil {
		return "", err
	}
	return result.AccessToken, nil
}
