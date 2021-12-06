package com.socialmedia.socialmedia.activemq.consumer;

import com.socialmedia.socialmedia.activemq.model.Message;
import com.socialmedia.socialmedia.email.EmailSenderService;
import com.socialmedia.socialmedia.user.IUserRepository;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageConsumer {
    @Autowired
    private final EmailSenderService emailSenderService;
    private final IUserRepository userRepository;

    public MessageConsumer(EmailSenderService emailSenderService, IUserRepository userRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
    }

    @JmsListener(destination = "MailBroker-queue")
    public void messageListener(Message message) {
        if(message.getMessage().equals("mail_send_all")) {
            List<User> userList = userRepository.findAll();
            userList.forEach(user -> {
                emailSenderService.sendSimpleEmail(user.getEmail(), "Hello Bro", "From Message Broker");
            });
        }
    }
}