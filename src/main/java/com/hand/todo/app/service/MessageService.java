package com.hand.todo.app.service;

import com.hand.todo.domain.entity.EmailRequest;
import com.hand.todo.domain.entity.MessageRequest;

import java.util.Map;

public interface MessageService {
    public String sendStation(MessageRequest messageRequest);
    String sendEmail(EmailRequest emailRequest);
    String sendFeishu(Map<String, String> map);
}
