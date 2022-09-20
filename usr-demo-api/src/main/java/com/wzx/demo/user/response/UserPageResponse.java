package com.wzx.demo.user.response;

import lombok.Data;

import java.util.List;

@Data
public class UserPageResponse {
    private Long total;

    private List<UserResponse> userResponses;
}
