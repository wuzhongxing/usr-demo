package com.wzx.demo.user.response;

import lombok.Data;

import java.util.List;

@Data
public class UserPageResponse {
    /**
     * 记录总条数
     */
    private Long total;
    /**
     * 查询到的用户信息
     */
    private List<UserResponse> userResponses;
}
