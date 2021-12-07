package com.socialmedia.socialmedia.message;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class APIMessage {
    private HttpStatus httpStatus;
    private final Map<String, String> error;
    private final Map<String, String> success;
    private final Object result;

    public APIMessage(HttpStatus httpStatus, Map<String, String> error, Map<String, String> success, Object result) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.success = success;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Map<String, String> getError() {
        return error;
    }

    public Map<String, String> getSuccess() {
        return success;
    }
}
