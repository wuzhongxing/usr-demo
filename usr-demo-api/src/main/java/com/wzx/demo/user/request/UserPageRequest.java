package com.wzx.demo.user.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserPageRequest {
    /**
     * 页数
     */
    @NotNull
    @Min(1)
    private Integer pageNum;
    /**
     * 每页条数
     */
    @NotNull
    @Min(1)
    @Max(20)
    private Integer pageSize;
}
