package com.hand.todo.domain.repository;

import com.hand.todo.api.dto.UserDTO;
import com.hand.todo.domain.entity.User;
import org.hzero.mybatis.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {
    List<UserDTO> selectList(UserDTO userDTO);
}
