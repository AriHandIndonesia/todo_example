package com.hand.todo.infra.repository.impl;

import com.hand.todo.domain.entity.Task;
import com.hand.todo.domain.repository.TaskRepository;
import com.hand.todo.infra.mapper.TaskMapper;
import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.mybatis.common.Criteria;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class TaskRepositoryImpl extends BaseRepositoryImpl<Task> implements TaskRepository {
    private  final TaskMapper taskMapper;
    public TaskRepositoryImpl(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    @Override
    public Task selectDetailByTaskNumber(String taskNumber) {
        Task params = new Task();
        params.setTaskNumber(taskNumber);
        List<Task> tasks = taskMapper.selectTask((params));
        return CollectionUtils.isNotEmpty(tasks) ? tasks.get(0) : null;
    }
    @Override
    public Page<Task> pageTask(Task task, PageRequest pageRequest) {
        return PageHelper.doPage(pageRequest, () -> taskMapper.selectTask(task));
    }
    @Override
    public List<Task> selectByEmployeeId(Long employeeId) {
        Task task = new Task();
        task.setEmployeeId(employeeId);
        return this.selectOptional(task, new Criteria()
                .select(Task.FIELD_ID, Task.FIELD_EMPLOYEE_ID, Task.FIELD_STATE, Task.FIELD_TASK_DESCRIPTION)
                .where(Task.FIELD_EMPLOYEE_ID)
        );
    }

    @Override
    public List<Task> selectAllTask(Task task) {
        return taskMapper.selectTask(task);
    }

}
