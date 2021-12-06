package com.socialmedia.socialmedia.activemq.model;

import java.io.Serializable;

public class Message implements Serializable {
    private String source;
    private String message;

    public Message(String source, String message) {
        this.source = source;
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "source='" + source + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
