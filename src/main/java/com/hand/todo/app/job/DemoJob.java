package com.hand.todo.app.job;

import com.hand.todo.app.service.MessageService;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@JobHandler("demo47835")
public class DemoJob implements IJobHandler {

    public static final Logger LOG = LoggerFactory.getLogger(DemoJob.class);

    @Autowired
    MessageService messageService;

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
       String result = messageService.sendFeishu(map);
        LOG.info("Name: Zamzam, number: 47835, email: muhammad.ari@hand-global.com " + result);
        return ReturnT.SUCCESS;
    }
}
