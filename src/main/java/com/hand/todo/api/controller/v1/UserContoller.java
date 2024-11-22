package com.hand.todo.api.controller.v1;

import com.hand.todo.app.service.UserService;
import com.hand.todo.config.SwaggerTags;
import com.hand.todo.domain.entity.User;
import com.hand.todo.domain.repository.UserRepository;
import org.hzero.boot.message.MessageClient;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.message.entity.Receiver;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.mybatis.helper.SecurityTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = SwaggerTags.USER)
@RestController("userController.v1")
@RequestMapping("/v1/users")
public class UserContoller extends BaseController {
    private final UserService userService;
    private final UserRepository userRepository;
    public UserContoller(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    MessageClient messageClient;

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "分页查询用户")
    @GetMapping
    public ResponseEntity<Page<User>> list(User user, PageRequest pageRequest) {
        return Results.success(userRepository.pageAndSort(pageRequest, user));
    }
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "创建 todo 用户")
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        // 简单数据校验
        this.validObject(user);
        // 创建用户
        return Results.success(userService.create(user));
    }
    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "删除 todo 用户")
    @DeleteMapping
    public ResponseEntity<User> delete(@RequestBody User user) {
        // 数据防篡改校验
        SecurityTokenHelper.validToken(user);
        // 删除用户
        userService.delete(user.getId());
        return Results.success();
    }
}
