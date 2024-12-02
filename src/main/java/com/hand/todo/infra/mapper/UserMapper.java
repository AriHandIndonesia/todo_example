package com.hand.todo.infra.mapper;

import com.hand.todo.api.dto.TaskDTO;
import com.hand.todo.api.dto.UserDTO;
import com.hand.todo.domain.entity.User;
import io.choerodon.mybatis.common.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<UserDTO> selectList(UserDTO userDTO);
}
