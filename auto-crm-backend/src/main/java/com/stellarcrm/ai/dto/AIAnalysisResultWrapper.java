package com.stellarcrm.ai.dto;

import java.time.LocalDateTime;

public class AIAnalysisResultWrapper<T> {
    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public AIAnalysisResultWrapper() {
        this.timestamp = LocalDateTime.now();
    }

    public AIAnalysisResultWrapper(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> AIAnalysisResultWrapper<T> success(T data) {
        return new AIAnalysisResultWrapper<>(200, "操作成功", data);
    }

    public static <T> AIAnalysisResultWrapper<T> error(String message) {
        return new AIAnalysisResultWrapper<>(500, message, null);
    }

    public static <T> AIAnalysisResultWrapper<T> error(int code, String message) {
        return new AIAnalysisResultWrapper<>(code, message, null);
    }

    // Getters and setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}