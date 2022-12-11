package com.example.back.api.service.jwt;

import java.util.Map;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    Map<String, Object> get(String key);
    String getNickname();
    boolean isUsable(String jwt);
}
