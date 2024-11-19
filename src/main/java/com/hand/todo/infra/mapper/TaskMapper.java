package com.hand.todo.infra.mapper;

import com.hand.todo.domain.entity.Task;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;

public interface TaskMapper extends BaseMapper<Task> {
    /**
     * 查询任务
     *
     * @param params 任务查询参数
     * @return Task
     */
    List<Task> selectTask(Task params);
}
