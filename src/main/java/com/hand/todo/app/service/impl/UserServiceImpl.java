package com.hand.todo.app.service.impl;

import com.hand.todo.app.service.UserService;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.domain.entity.User;
import com.hand.todo.domain.repository.TaskRepository;
import com.hand.todo.domain.repository.UserRepository;
import io.choerodon.core.exception.CommonException;
import org.apache.commons.collections4.CollectionUtils;
import org.hzero.mybatis.domian.Condition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        userRepository.insert(user);
        return user;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        User exist = userRepository.selectByPrimaryKey(userId);
        if (exist == null) {
            throw new CommonException("htdo.warn.user.notFound");
        }
        // 删除用户
        userRepository.deleteByPrimaryKey(userId);
        // 删除与用户关联的任务
        List<Task> tasks = taskRepository.selectByEmployeeId(userId);
        if (CollectionUtils.isNotEmpty(tasks)) {
            taskRepository.batchDelete(tasks);
        }
    }

    @Override
    public User getUserbyId(String employeeNumber) {

        User user = new User();
        user.setEmployeeNumber(employeeNumber);

//        Condition.Criteria
//        User result = userRepository.selectOneOptional(user,);

        return userRepository.selectByPrimaryKey(6L);
    }
}
