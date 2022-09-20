package com.wzx.demo.user.manager;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.wzx.demo.common.Response;
import com.wzx.demo.entity.User;
import com.wzx.demo.user.UserService;
import com.wzx.demo.user.api.UserApi;
import com.wzx.demo.user.event.RegisterEvent;
import com.wzx.demo.user.request.UserAddRequest;
import com.wzx.demo.user.request.UserPageRequest;
import com.wzx.demo.user.request.UserUpdateRequest;
import com.wzx.demo.user.response.UserPageResponse;
import com.wzx.demo.user.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.wzx.demo.common.CommonResponse.USER_NOT_FOUND;

@Validated
@Slf4j
@Service
public class UserApiManager implements UserApi {

    @Value("${demo.send.flag:true}")
    private boolean sendEmail = false;

    @Resource
    ApplicationContext applicationContext;

    @Resource
    UserService userService;

    @Override
    public Response<UserResponse> getUser(@NotNull Long uid) {
        User user = userService.getUser(uid);
        if (Objects.isNull(user)) {
            return Response.create(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
        }
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return Response.success(userResponse);
    }

    @Override
    public Response<Long> registerUser(@NotNull @Valid UserAddRequest user) {
        User addUser = new User();
        BeanUtils.copyProperties(user, addUser);
        Long uid = userService.registerUser(addUser);
        // 发送邮件
        if (Objects.nonNull(uid) && sendEmail) {
            applicationContext.publishEvent(new RegisterEvent("userRegister", uid));
        }
        return Response.success(uid);
    }

    @Override
    public Response<Boolean> updateUser(@NotNull @Valid UserUpdateRequest user) {
        User addUser = new User();
        BeanUtils.copyProperties(user, addUser);
        return Response.success(userService.updateUser(addUser));
    }

    @Override
    public Response<Boolean> delUser(@NotNull List<Long> uidList) {
        return Response.success(userService.delUser(uidList));
    }

    @Override
    public Response<UserPageResponse> queryUsersByPage(@NotNull @Valid UserPageRequest pageRequest) {

        PageInfo<User> result = userService.findUserByPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        // 组装返回数据
        UserPageResponse response = new UserPageResponse();
        response.setTotal(result.getTotal());
        if (CollectionUtils.isEmpty(result.getList())) {
            response.setUserResponses(Lists.newArrayList());
        } else {
            response.setUserResponses(result.getList().stream().map(user -> {
                UserResponse userResponse = new UserResponse();
                BeanUtils.copyProperties(user, userResponse);
                return userResponse;
            }).collect(Collectors.toList()));
        }

        return Response.success(response);
    }
}
