package com.wzx.demo.user.api;

import com.wzx.demo.common.Response;
import com.wzx.demo.user.request.UserAddRequest;
import com.wzx.demo.user.request.UserPageRequest;
import com.wzx.demo.user.request.UserUpdateRequest;
import com.wzx.demo.user.response.UserPageResponse;
import com.wzx.demo.user.response.UserResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserApi {
    /**
     * 查询用户
     *
     * @param uid 用户uid
     * @return 用户信息详情
     */
    Response<UserResponse> getUser(@NotNull Long uid);

    /**
     * 用户注册
     *
     * @param user 注册信息
     * @return 成功后的用户uid
     */
    Response<Long> registerUser(@NotNull @Valid UserAddRequest user);

    /**
     * 用户修改
     *
     * @param user 信息信息
     * @return 是否修改成功
     */
    Response<Boolean> updateUser(@NotNull @Valid UserUpdateRequest user);

    /**
     * 用户删除
     *
     * @param uidList 待删除的uid列表
     * @return 是否删除成功
     */
    Response<Boolean> delUser(@NotNull List<Long> uidList);

    /**
     * 用户分页查询
     *
     * @param pageRequest 分页参数
     * @return 用户分页详情
     */
    Response<UserPageResponse> queryUsersByPage(@NotNull @Valid UserPageRequest pageRequest);

}
