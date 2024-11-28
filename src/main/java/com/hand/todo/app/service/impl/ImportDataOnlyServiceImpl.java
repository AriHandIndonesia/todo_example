package com.hand.todo.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.infra.repository.impl.TaskRepositoryImpl;


import com.hand.todo.infra.repository.impl.UserRepositoryImpl;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.app.service.ValidatorHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ImportService(templateCode = "DEMO-CLIENT-47835-")
public class ImportDataOnlyServiceImpl implements IDoImportService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskRepositoryImpl taskRepository;

    @Override
    public Boolean doImport(String data) {
        Task task;
        try {
            task = objectMapper.readValue(data, Task.class);
            //Insert
            taskRepository.insertSelective(task);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
//        return null;
    }
}
