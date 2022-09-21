package com.wzx.demo.user.request;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class UserAddRequest {
    /**
     * 用户姓名
     */
    @NotBlank
    private String name;

    /**
     * 用户email
     */
    @NotNull
    @Email
    private String email;
}
