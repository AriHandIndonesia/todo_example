package com.hand.todo.app.service.impl;

import com.hand.todo.api.dto.TaskDTO;
import com.hand.todo.app.service.TaskService;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.domain.repository.TaskRepository;
import com.hand.todo.infra.mapper.TaskMapper;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import org.hzero.boot.platform.code.builder.CodeRuleBuilder;
import org.hzero.boot.platform.lov.adapter.LovAdapter;
import org.hzero.boot.platform.lov.dto.LovValueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CodeRuleBuilder codeRuleBuilder;

    @Autowired
    private LovAdapter lovAdapter;

    @Autowired
    private TaskMapper taskMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task create(Task task) {
        /*V 1.0 [S]
        // 生成任务编号
//        task.generateTaskNumber();
        V 1.0 [E] */
        //V 1.1 [S]
        String name = DetailsHelper.getUserDetails().getRealName();
        Map<String, String> variableMap = new HashMap<>();
        variableMap.put("customSegment", name + "-");
        String taskNumber = codeRuleBuilder.generateCode("DEMOCORU47835",variableMap);
        task.setTaskNumber(taskNumber);

        //check task type
        boolean indicator = false;
        if (task.getTaskType() != null) {
            List<LovValueDTO> taskTypes = lovAdapter.queryLovValue("DEMO-47835-TASKTYPE", task.getTenantId());
            for (int i = 0; i < taskTypes.size(); i++) {
                if (taskTypes.get(i).getValue().equals(task.getTaskType())) {
                    indicator = true;
                    break;
                }
            }
        }else {
            throw new CommonException("demo-47835.tasktype_null");
        }
        if (!indicator){
            throw new CommonException("demo-47835.tasktype_not_found");
        }
        //V 1.1 [E]
        // 插入数据
        taskRepository.insert(task);
        return task;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task update(Task task) {
        Task exist = taskRepository.selectByPrimaryKey(task);
        if (exist == null) {
            throw new CommonException("htdo.warn.task.notFound");
        }
        // 更新指定字段
        taskRepository.updateOptional(task,
                Task.FIELD_STATE,
                Task.FIELD_TASK_DESCRIPTION
        );
        return task;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskNumber(String taskNumber) {
        Task task = new Task();
        task.setTaskNumber(taskNumber);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> selectAllTask(String taskNumber) {
        return taskRepository.selectAllTask(taskRepository.selectDetailByTaskNumber(taskNumber));
    }

    @Override
    public List<TaskDTO> selectList(TaskDTO taskDTO) {
        return taskMapper.getTaskList(taskDTO);
    }
}
