package com.stellarcrm.service.auth.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 简单实现，实际项目中应该从数据库查询用户信息
        if ("admin".equals(username)) {
            List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("customer:create"),
                new SimpleGrantedAuthority("customer:read"),
                new SimpleGrantedAuthority("customer:update"),
                new SimpleGrantedAuthority("customer:delete")
            );
            return new User(username, "$2a$10$8K1p/a0dhrxiowP.dnkgNORTWgdEDHn5L2/xjpEWuC.QQv4rKO9jO", authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}