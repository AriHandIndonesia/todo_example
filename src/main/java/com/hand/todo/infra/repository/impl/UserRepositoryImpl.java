package com.hand.todo.infra.repository.impl;

import com.hand.todo.api.dto.UserDTO;
import com.hand.todo.domain.entity.User;
import com.hand.todo.domain.repository.UserRepository;
import com.hand.todo.infra.mapper.TaskMapper;
import com.hand.todo.infra.mapper.UserMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDTO> selectList(UserDTO userDTO) {
        return userMapper.selectList(userDTO);
    }
}
