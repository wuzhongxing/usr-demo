package com.wzx.demo.user.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    /**
     * uid
     */
    private Long uid;
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date creteTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 邮件发送状态
     */
    private Integer emailStatus;

    /**
     * 邮件发送时间
     */
    private Date sendTime;

}
