package com.stellarcrm.service.auth.impl;

import com.stellarcrm.dto.auth.LoginRequest;
import com.stellarcrm.dto.auth.LoginResponse;
import com.stellarcrm.service.auth.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expiration}")
    private Long jwtExpiration;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        // 在构造函数中打印BCrypt编码的密码，用于验证
        logger.info("Encoded password for 'admin123': {}", passwordEncoder.encode("admin123"));
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        logger.info("Attempting login for username: {}", request.getUsername());
        
        // 简单的用户名密码验证（实际项目中应该查询数据库）
        // 使用正确的BCrypt编码密码
        String encodedPassword = passwordEncoder.encode("admin123");
        
        logger.info("Checking username: expected 'admin', got '{}'", request.getUsername());
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), encodedPassword);
        logger.info("Checking password: provided password matches encoded: {}", passwordMatches);
        
        // 对于演示目的，我们直接比较密码而不是使用编码后的密码
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            logger.info("Login successful for user: {}", request.getUsername());
            String token = generateToken(request.getUsername());
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setUsername(request.getUsername());
            response.setUserId(1L);
            return response;
        } else {
            logger.warn("Login failed for username: {}", request.getUsername());
            throw new RuntimeException("Invalid username or password");
        }
    }

    private String generateToken(String username) {
        SecretKey key = getSigningKey();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    
    private SecretKey getSigningKey() {
        // 如果密钥不是有效的Base64字符串，则使用直接的字节转换
        try {
            byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            // 如果Base64解码失败，则直接使用密钥字节
            return Keys.hmacShaKeyFor(jwtSecret.getBytes());
        }
    }
}