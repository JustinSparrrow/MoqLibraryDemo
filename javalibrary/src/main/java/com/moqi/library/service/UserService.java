package com.moqi.library.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.config.WxConfig;
import com.moqi.library.dao.UserDao;
import com.moqi.library.dao.bo.User;
import com.moqi.library.mapper.mapper.UserMapper;
import com.moqi.library.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;
    private final WxConfig wxConfig;
    private final TokenUtil tokenUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public UserService(UserDao userDao, WxConfig wxConfig, TokenUtil tokenUtil) {
        this.userDao = userDao;
        this.wxConfig = wxConfig;
        this.tokenUtil = tokenUtil;
    }

    /**
     * 微信小程序登录
     *
     * @param code 微信前端传来的临时登录凭证
     * @return 用户的登录 Token
     * @throws BusinessException 异常处理
     */
    public String wxLogin(String code) throws BusinessException {
        try {
            // 1. 通过 code 向微信服务器获取 openid 和 session_key
            String url = String.format("%s?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    wxConfig.getLoginUrl(), wxConfig.getAppId(), wxConfig.getAppSecret(), code);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if (jsonNode.has("errcode")) {
                throw new BusinessException(ReturnNo.AUTH_INVALID_ACCOUNT, "微信登录失败：" + jsonNode.get("errmsg").asText());
            }

            String openid = jsonNode.get("openid").asText();

            // 2. 根据 openid 查询用户
            Optional<User> existingUser = Optional.ofNullable(userDao.getUserByOpenid(openid));
            User user;
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                // 3. 用户不存在则注册新用户
                user = new User();
                user.setOpenid(openid);
                user.setRole("user");
                userDao.createUser(user);
            }

            // 4. 生成用户登录 Token
            if (user.getId() == null || user.getRole() == null) {
                throw new BusinessException(ReturnNo.AUTH_INVALID_ACCOUNT, "用户信息不完整，无法生成 Token");
            }

            return tokenUtil.generateToken(user.getId(), user.getRole());

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnNo.AUTH_INVALID_ACCOUNT, "微信登录失败，请重试");
        }
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public User getUserById(Long userId) throws BusinessException {
        return userDao.getUserById(userId);
    }

    /**
     * 更新用户信息
     *
     * @param userId 用户ID
     * @param user 更新后的用户信息
     */
    public void updateUserInfo(Long userId, User user) throws BusinessException {
        User existingUser = getUserById(userId);
        if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
        if (user.getNickname() != null) existingUser.setNickname(user.getNickname());
        if (user.getAvatar() != null) existingUser.setAvatar(user.getAvatar());

        userDao.updateUserInfo(existingUser);
    }
}
