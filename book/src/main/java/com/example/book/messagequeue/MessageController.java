package com.example.book.messagequeue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService sender;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String msg) {
        sender.send(msg);
        return "Message sent!";
    }
}
