package com.socialmedia.socialmedia.exception;


import com.socialmedia.socialmedia.activemq.model.Message;
import com.socialmedia.socialmedia.message.APIMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<APIMessage> handleApiRequestException(ApiRequestException apiRequestException) {
        APIMessage message = apiRequestException.getAPIMessage();
        return new ResponseEntity<APIMessage>(message, message.getHttpStatus());
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ApiException> handleIllegalStateException(IllegalStateException illegalStateException) {
        ApiException apiException = new ApiException(illegalStateException.getMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiException>(apiException, apiException.getHttpStatus());
    }
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiException> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException
                                                                                                 httpRequestMethodNotSupportedException) {
        ApiException apiException = new ApiException(httpRequestMethodNotSupportedException.getMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiException>(apiException, apiException.getHttpStatus());
    }
}
