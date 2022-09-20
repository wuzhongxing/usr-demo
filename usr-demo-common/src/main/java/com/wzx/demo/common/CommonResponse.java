package com.wzx.demo.common;

public class CommonResponse {
    public static final Response SUCCESS = Response.create("0", "success");
    public static final Response UID_ERROR = Response.create("1001", "uid错误");
    public static final Response USER_NOT_FOUND = Response.create("1002", "用户不存在");
    public static final Response PARAM_ERROR = Response.create("2001", "参数错误");
}
