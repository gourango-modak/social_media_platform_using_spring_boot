package com.socialmedia.socialmedia.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionControllerForSocket {
    @MessageMapping("/login-user-notify")
    @SendTo("/topic/login-notify")
    public ActionMessage getActionByMessage() {
        return new ActionMessage("refresh_active_user");
    }
}
