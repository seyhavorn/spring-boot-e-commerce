package com.seyhavorn.springbootecommerce.helper;

public record ApiResponse(boolean success, String message, Object data) {

}
