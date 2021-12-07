package com.socialmedia.socialmedia.exception;

import com.socialmedia.socialmedia.message.APIMessage;

public class ApiRequestException extends RuntimeException {

    private APIMessage apiMessage;

    public ApiRequestException(APIMessage APIMessage) {
        this.apiMessage = APIMessage;
    }

    public APIMessage getAPIMessage() {
        return apiMessage;
    }

    public void setMessage(APIMessage APIMessage) {
        this.apiMessage = APIMessage;
    }
}
