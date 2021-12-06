package com.socialmedia.socialmedia.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
    @PostMapping("/send_mail")
    public String sendEmail(@RequestBody Map<String,Object> body) {
        emailSenderService.sendSimpleEmail(body.get("Email").toString(), "Hi Bro", "Say Hi");
        return "Mail has Sent";
    }
}
