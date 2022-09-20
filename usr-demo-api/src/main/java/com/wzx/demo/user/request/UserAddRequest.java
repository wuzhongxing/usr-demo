package com.wzx.demo.user.request;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class UserAddRequest {
    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;
}
