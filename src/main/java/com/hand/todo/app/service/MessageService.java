package com.hand.todo.app.service;

import com.hand.todo.domain.entity.EmailRequest;
import com.hand.todo.domain.entity.MessageRequest;

public interface MessageService {
    public String sendStation(MessageRequest messageRequest);
    String sendEmail(EmailRequest emailRequest);
}
