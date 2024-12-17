package com.moqi.library.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilTest {

    private TokenUtil tokenUtil;
    private SecretKey secretKey; // 正确类型的密钥

    private final long userId = 12345L;
    private final String role = "user";

    @BeforeEach
    void setUp() {
        tokenUtil = new TokenUtil();

        // 生成 SecretKey 对象
        String base64Key = "2Q66Ns/+ad5GTx9mec3EfOUxL2CqIngieTqsf6lc2DvW9S2iSBZlVdAWff7xVqKrDju/YchrOOnLTD0TrqfxcQ=="; // 配置中的 Base64 密钥
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        secretKey = Keys.hmacShaKeyFor(keyBytes);

        // 使用 ReflectionTestUtils 注入密钥
        ReflectionTestUtils.setField(tokenUtil, "secretKey", secretKey);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        // Act: 生成 Token
        String token = tokenUtil.generateToken(userId, role);

        // Assert
        assertNotNull(token, "Token should not be null");

        // 解析 Token
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(String.valueOf(userId), claims.getSubject(), "Subject should match userId");
        assertEquals(role, claims.get("role"), "Role should match");
    }

    @Test
    void parseToken_ShouldReturnCorrectUserId() {
        // Arrange: 生成测试 Token
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24小时过期
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        // Act
        Long parsedUserId = tokenUtil.parseToken(token);

        // Assert
        assertEquals(userId, parsedUserId, "Parsed userId should match original userId");
    }

    @Test
    void parseToken_WhenTokenInvalid_ShouldThrowException() {
        // Arrange: 构造一个无效 Token
        String invalidToken = "invalid.token.here";

        // Act & Assert
        assertThrows(io.jsonwebtoken.JwtException.class, () -> {
            tokenUtil.parseToken(invalidToken);
        }, "Parsing an invalid token should throw JwtException");
    }
}