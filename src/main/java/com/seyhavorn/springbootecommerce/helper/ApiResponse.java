package com.seyhavorn.springbootecommerce.helper;

import lombok.Data;

public record ApiResponse(boolean success, String message, Object data) {
//    private boolean success;
//    private String message;
//    private Object data;
//
//    public ApiResponse(boolean success, String message, Object data) {
//        this.success = success;
//        this.message = message;
//        this.data = data;
//    }
}
