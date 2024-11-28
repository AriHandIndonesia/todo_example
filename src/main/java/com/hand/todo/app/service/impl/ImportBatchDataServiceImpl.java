package com.hand.todo.app.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.infra.repository.impl.TaskRepositoryImpl;
import org.hzero.boot.imported.app.service.IBatchImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ImportService(templateCode = "DEMO-CLIENT-47835")
public class ImportBatchDataServiceImpl implements IBatchImportService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TaskRepositoryImpl taskRepository;

    @Override
    public Boolean doImport(List<String> data) {
        Boolean flag = true;
        List<Task> taskList = new ArrayList<>();
        try {
            for (int i = 0; i < data.size(); i++) {
                Task task = objectMapper.readValue(data.get(i), Task.class);
                taskList.add(task);
            }
            taskRepository.batchInsert(taskList);
        }catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
