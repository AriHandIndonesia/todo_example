package com.hand.todo.api.controller.v1;

import com.hand.todo.app.service.MessageService;
import com.hand.todo.config.SwaggerTags;
import com.hand.todo.domain.entity.EmailRequest;
import com.hand.todo.domain.entity.MessageRequest;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import org.hzero.boot.message.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerTags.MESSAGE)
@RestController("messageController.v1")
@RequestMapping("/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/messages")
    public String sendMessage(@RequestBody MessageRequest message){
        return messageService.sendStation(message);
    }

    @Permission(level = ResourceLevel.SITE)
    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailRequest message){
        return messageService.sendEmail(message);
    }
}
