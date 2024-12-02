package com.hand.todo.app.service;

import com.hand.todo.api.dto.TaskDTO;
import com.hand.todo.domain.entity.Task;

import java.util.List;
import java.util.stream.Stream;

public interface TaskService {
    /**
     * 创建任务
     *
     * @param task 任务
     * @return Task
     */
    Task create(Task task);
    /**
     * 更新任务
     *
     * @param task 任务
     * @return Task
     */
    Task update(Task task);
    /**
     * 根据任务编号删除
     *
     * @param taskNumber 任务编号
     */
    void deleteByTaskNumber(String taskNumber);

    List<Task> selectAllTask(String taskNumber);

    List<TaskDTO> selectList(TaskDTO taskDTO);
}
