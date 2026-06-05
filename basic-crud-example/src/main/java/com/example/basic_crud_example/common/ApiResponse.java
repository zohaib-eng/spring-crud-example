package com.example.basic_crud_example.common;

import java.time.Instant;
import java.util.UUID;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private String errorCode;
    private T data;
    private Meta meta;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, String errorCode, T data, Meta meta) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.data = data;
        this.meta = meta;
    }

    // success response helper
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                null,
                data,
                new Meta(Instant.now(), UUID.randomUUID().toString(), "1.0")
        );
    }

    // error response helper
    public static <T> ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponse<>(
                false,
                message,
                errorCode,
                null,
                new Meta(Instant.now(), UUID.randomUUID().toString(), "1.0")
        );
    }

    // ================= META CLASS =================
    public static class Meta {
        private Instant timestamp;
        private String requestId;
        private String version;

        public Meta() {
        }

        public Meta(Instant timestamp, String requestId, String version) {
            this.timestamp = timestamp;
            this.requestId = requestId;
            this.version = version;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public String getRequestId() {
            return requestId;
        }

        public String getVersion() {
            return version;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    // getters & setters (optional but recommended)
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }
}