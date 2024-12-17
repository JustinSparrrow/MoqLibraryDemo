package com.moqi.library.service;

import com.moqi.core.exception.BusinessException;
import com.moqi.core.model.ReturnNo;
import com.moqi.library.config.WxConfig;
import com.moqi.library.dao.UserDao;
import com.moqi.library.dao.bo.User;
import com.moqi.library.util.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private WxConfig wxConfig;

    @Mock
    private TokenUtil tokenUtil;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService;

    private final String testCode = "testCode";
    private final String testOpenId = "testOpenId";
    private final String testToken = "testToken";
    private final Long testUserId = 123L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(wxConfig.getLoginUrl()).thenReturn("https://api.weixin.qq.com/sns/jscode2session");
        when(wxConfig.getAppId()).thenReturn("testAppId");
        when(wxConfig.getAppSecret()).thenReturn("testAppSecret");
    }

    @Test
    void wxLogin_ShouldReturnToken_WhenUserExists() throws Exception {
        // Arrange
        String wxResponse = "{\"openid\": \"" + testOpenId + "\"}";
        User existingUser = new User();
        existingUser.setId(testUserId);
        existingUser.setOpenid(testOpenId);
        existingUser.setRole("user");

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(ResponseEntity.ok(wxResponse));
        when(userDao.getUserByOpenid(testOpenId)).thenReturn(existingUser);
        when(tokenUtil.generateToken(testUserId, "user")).thenReturn(testToken);

        // Act
        String token = userService.wxLogin(testCode);

        // Assert
        assertEquals(testToken, token);
        verify(userDao, never()).createUser(any(User.class));
    }

    @Test
    void wxLogin_ShouldCreateUser_WhenUserDoesNotExist() throws Exception {
        // Arrange
        String wxResponse = "{\"openid\": \"testOpenId\"}";

        // Mock RestTemplate 返回微信响应
        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(ResponseEntity.ok(wxResponse));

        // Mock userDao 和 tokenUtil
        when(userDao.getUserByOpenid("testOpenId")).thenReturn(null);
        when(tokenUtil.generateToken(anyLong(), eq("user"))).thenReturn("mockToken");

        // Act
        String token = userService.wxLogin("testCode");

        // Assert
        assertNotNull(token);
        assertEquals("mockToken", token);
        verify(userDao, times(1)).createUser(any(User.class));
    }

    @Test
    void wxLogin_ShouldThrowException_WhenWxLoginFails() {
        // Arrange
        String wxErrorResponse = "{\"errcode\": 40029, \"errmsg\": \"invalid code\"}";

        when(restTemplate.getForEntity(anyString(), eq(String.class)))
                .thenReturn(ResponseEntity.ok(wxErrorResponse));

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.wxLogin(testCode);
        });
        assertEquals(ReturnNo.AUTH_INVALID_ACCOUNT, exception.getErrno());
    }

    @Test
    void getUserById_ShouldReturnUser() {
        // Arrange
        User user = new User();
        user.setId(testUserId);
        user.setOpenid(testOpenId);

        when(userDao.getUserById(testUserId)).thenReturn(user);

        // Act
        User result = userService.getUserById(testUserId);

        // Assert
        assertNotNull(result);
        assertEquals(testUserId, result.getId());
    }

    @Test
    void updateUserInfo_ShouldUpdateUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(testUserId);
        existingUser.setPhone("12345");

        User updatedUser = new User();
        updatedUser.setPhone("67890");
        updatedUser.setNickname("newNickname");

        when(userDao.getUserById(testUserId)).thenReturn(existingUser);

        // Act
        userService.updateUserInfo(testUserId, updatedUser);

        // Assert
        verify(userDao, times(1)).updateUserInfo(existingUser);
        assertEquals("67890", existingUser.getPhone());
        assertEquals("newNickname", existingUser.getNickname());
    }
}