package com.wzx.demo.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {
    @NotNull
    private Long uid;

    private String name;

    private String email;
}
