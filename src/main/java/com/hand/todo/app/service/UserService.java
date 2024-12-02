package com.hand.todo.app.service;

import com.hand.todo.api.dto.UserDTO;
import com.hand.todo.domain.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 创建用户
     *
     * @param user User
     * @return User
     */
    User create(User user);
    /**
     * 删除用户(同时删除任务)
     *
     * @param userId 用户ID
     */
    void delete(Long userId);

    User getUserbyId(String employeeNumber);

    List<UserDTO> exportData(UserDTO userDTO);
}
