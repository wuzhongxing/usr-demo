package com.wzx.demo.user.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private Long uid;

    private String name;

    private String email;

    private Date creteTime;

    private Date modifyTime;

    private Integer isDel;

    private Integer emailStatus;

    private Date sendTime;

}
