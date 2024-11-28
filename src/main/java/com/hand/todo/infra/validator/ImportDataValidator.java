package com.hand.todo.infra.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.infra.repository.impl.UserRepositoryImpl;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.imported.app.service.ValidatorHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidator;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidators;
import org.springframework.beans.factory.annotation.Autowired;

@ImportValidators({
        @ImportValidator(templateCode = "DEMO-CLIENT-47835")
})
public class ImportDataValidator extends ValidatorHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public boolean validate(String data) {
        Task task = null;
        try {
            task = objectMapper.readValue(data, Task.class);
            if (task.getTaskNumber().contains(".*[^A-Za-z].*")){
                throw new CommonException("Task Number can only be english letter");
            }
            if (!userRepository.selectByPrimaryKey(task.getEmployeeId()).getId().equals(task.getEmployeeId())){
                throw new CommonException("Employee Not found");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
