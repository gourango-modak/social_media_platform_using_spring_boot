package com.socialmedia.socialmedia.socket;

public class ActionMessage {
    private String name;

    public ActionMessage(String name) {
        this.name = name;
    }

    public ActionMessage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
