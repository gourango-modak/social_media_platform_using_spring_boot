package com.socialmedia.socialmedia.activemq.consumer;

import com.socialmedia.socialmedia.activemq.model.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "MailBroker-queue")
    public void messageListener(Message message) {
        System.out.println(message);
    }
}