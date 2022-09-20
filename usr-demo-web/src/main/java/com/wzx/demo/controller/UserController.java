package com.wzx.demo.controller;

import com.wzx.demo.common.Response;
import com.wzx.demo.user.api.UserApi;
import com.wzx.demo.user.request.UserAddRequest;
import com.wzx.demo.user.request.UserPageRequest;
import com.wzx.demo.user.request.UserUpdateRequest;
import com.wzx.demo.user.response.UserPageResponse;
import com.wzx.demo.user.response.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
public class UserController {

    @Resource
    UserApi userApi;

    /**
     * 查找用户
     *
     * @param uid 用户uid
     * @return 用户详情
     */
    @GetMapping("/usr")
    public Response<UserResponse> getUser(@NotNull(message = "uid is null") Long uid) {
        return userApi.getUser(uid);
    }

    /**
     * 删除用户
     *
     * @param uidList 待删除的uid列表
     * @return 是否删除成功
     */
    @PostMapping("/del")
    public Response<Boolean> delUser(@RequestBody @NotNull(message = "uidList is null") List<Long> uidList) {
        return userApi.delUser(uidList);
    }

    /**
     * 用户注册
     *
     * @param userVO 用户注册数据
     * @return 用户uid
     */
    @PostMapping("/register")
    public Response<Long> registerUser(@RequestBody UserAddRequest userVO) {
        return userApi.registerUser(userVO);
    }

    /**
     * 用户修改
     *
     * @param userVO 待修改数据
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Response<Boolean> updateUser(@Valid @RequestBody UserUpdateRequest userVO) {
        return userApi.updateUser(userVO);
    }

    /**
     * 用户分页查询
     *
     * @param pageRequest 请求参数
     * @return 分页数据
     */
    @PostMapping("/page")
    public Response<UserPageResponse> queryUsersByPage(@Valid @RequestBody UserPageRequest pageRequest) {
        return userApi.queryUsersByPage(pageRequest);
    }
}
