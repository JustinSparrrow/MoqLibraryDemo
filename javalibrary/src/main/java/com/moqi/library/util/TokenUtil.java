package com.moqi.library.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenUtil {

    private SecretKey secretKey;

    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKeyString) {
        // 将 Base64 编码的密钥字符串转换为 SecretKey
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // Token 过期时间（24小时）
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // 构造函数中初始化 SecretKey
    public TokenUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 生成安全密钥
    }

    /**
     * 生成 JWT Token
     *
     * @param userId 用户ID
     * @param role   用户角色
     * @return 生成的 Token 字符串
     */
    public String generateToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey) // 使用生成的安全密钥
                .compact();
    }

    /**
     * 解析 Token，获取用户ID
     *
     * @param token JWT Token 字符串
     * @return 用户ID
     */
    public Long parseToken(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }
}