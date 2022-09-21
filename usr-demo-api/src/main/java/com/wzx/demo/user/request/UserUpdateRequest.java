package com.wzx.demo.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {
    /**
     * 用户uid
     */
    @NotNull
    private Long uid;

    private String name;
// email 不可修改
//    private String email;
}
