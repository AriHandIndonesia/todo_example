package com.hand.todo.infra.repository.impl;

import com.hand.todo.domain.entity.User;
import com.hand.todo.domain.repository.UserRepository;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {
}
