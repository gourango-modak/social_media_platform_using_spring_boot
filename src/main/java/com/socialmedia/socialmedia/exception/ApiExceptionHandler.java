package com.socialmedia.socialmedia.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException apiRequestException) {
        ApiException apiException = new ApiException(apiRequestException.getMessage(),
                apiRequestException.getHttpStatus());
        return new ResponseEntity<ApiException>(apiException, apiException.getHttpStatus());
    }
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ApiException> handleIllegalStateException(IllegalStateException illegalStateException) {
        ApiException apiException = new ApiException(illegalStateException.getMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiException>(apiException, apiException.getHttpStatus());
    }
}
