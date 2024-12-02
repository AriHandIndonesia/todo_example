package com.hand.todo.app.service.impl;

import com.hand.todo.api.dto.TaskDTO;
import com.hand.todo.api.dto.UserDTO;
import com.hand.todo.app.service.TaskService;
import com.hand.todo.app.service.UserService;
import com.hand.todo.domain.entity.Task;
import com.hand.todo.domain.entity.User;
import com.hand.todo.domain.repository.TaskRepository;
import com.hand.todo.domain.repository.UserRepository;
import io.choerodon.core.exception.CommonException;
import io.choerodon.core.oauth.DetailsHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.hzero.core.cache.ProcessCacheValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    TaskService taskService;


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

    @Override
    public List<UserDTO> exportData(UserDTO userDTO) {
        List<UserDTO> userList = userRepository.selectList(userDTO);
        List<Long> userIdList = new ArrayList<>();
        userList.forEach(user -> userIdList.add(user.getId()));
        Map<Long, List<TaskDTO>> taskMap = taskService.selectList(new TaskDTO().setEmpIdList(userIdList))
                .stream()
                .collect(Collectors.groupingBy(TaskDTO::getEmployeeId));
        userList.forEach(user -> user.setTaskList(taskMap.get(user.getId())));
        return userList;
    }


}
