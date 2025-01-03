package com.hand.todo.app.service.impl;

import com.hand.todo.app.service.MessageService;
import com.hand.todo.app.service.UserService;
import com.hand.todo.domain.entity.EmailRequest;
import com.hand.todo.domain.entity.MessageRequest;
import com.hand.todo.domain.entity.User;
import org.hzero.boot.message.MessageClient;
import org.hzero.boot.message.entity.FlyBookMsgType;
import org.hzero.boot.message.entity.Message;
import org.hzero.boot.message.entity.Receiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageClient messageClient;

    @Autowired
    UserService userService;

//    Integer tenantId =
    private String SENDER = "muhammad.ari@hand-global.com";

    @Override
    public String sendStation(MessageRequest messageRequest) {
        //masukan ke receiver
//        HashMap<String,String> =
//        List<Receiver> receivers = messageClient.receiver(messageRequest.getReceiverId(), messageRequest.getOrganizationId());        //masukan message
        Receiver receiver = new Receiver();
        receiver.setUserId(messageRequest.getReceiverId());
        receiver.setTargetUserTenantId(messageRequest.getOrganizationId());

        List<String> message = checkMessage(messageRequest.getMessage());

        Map<String,String> param = new HashMap<>();
        param.put("message1", message.get(0));
        param.put("message2", message.get(1));
        param.put("sender_email", SENDER);

        //kirim message
        Message result = messageClient.sendWebMessage(
          messageRequest.getOrganizationId(),
          "TEST-47835",
          "en_US",
          Collections.singletonList(receiver),
          param
        );
        //kirim response
        return result.getMessageTypeCode();
    }

    @Override
    public String sendEmail(EmailRequest emailRequest) {
        Receiver receiver = new Receiver();
        receiver.setEmail(emailRequest.getEmail());
        receiver.setTargetUserTenantId(emailRequest.getOrganizationId());


        Map param = new HashMap<>();
        param.put("contextJSON", emailRequest.getParam());


        //kirim message
        Message result = messageClient.sendEmail(
                emailRequest.getOrganizationId(),
                "TEST-47835-2",
                "TEST-47835-2",
                "en_US",
                Collections.singletonList(receiver),
                param
        );
        //kirim response
        return result.toString();
    }

    @Override
    public String sendFeishu(Map<String, String> map) {
        //receiver
        Map<String, String> receiverMap = new HashMap<>();
        receiverMap.put("email",map.get("receiverEmail"));
        receiverMap.put("EmployeeId",map.get("receiverEmpId"));

        FlyBookMsgType msgType = FlyBookMsgType.TEXT;

        //arg
        Map<String ,Object> param = new HashMap<>();
        //get user from db for param in template
        User user = userService.getUserbyId(map.get("employeeId"));
        param.put("userName", user.getEmployeeName());
        param.put("empNumber", user.getEmployeeNumber());
        param.put("email", user.getEmail());

        String tenantId = map.get("tenantId");
        Long tenantIdL = Long.parseLong(tenantId);
        //kirim feishu
//        Message result = messageClient.sendFlyBook(
//                tenantIdL,
//                map.get("serverCode"),
//                map.get("messageTemplate"),
//                msgType,
//                map.get("lang"),
//                Collections.singletonList(receiverMap),
//                param
//                );
//
//        return result.toString();
        return "";
    }

    private List<String> checkMessage(List<String> messages){
        //value default message
        String defaultMessage = "Ini Default Message";
        //cek message
        if (messages == null || messages.isEmpty()){
            messages.add(defaultMessage);
            return messages;
        }
        if (messages.size() < 2){
            messages.add(defaultMessage);
        }

        return messages;
    }
}
