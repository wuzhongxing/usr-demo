package com.wzx.demo.user.impl;

import com.github.pagehelper.PageInfo;
import com.wzx.demo.BaseTest;
import com.wzx.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class UserServiceImplTest extends BaseTest {

    @Resource
    private UserServiceImpl userService;


    @Test
    public void saveUser() {
        User user = new User();
        user.setEmail(System.currentTimeMillis() + "@a.com");
        user.setName("测试" + System.currentTimeMillis());
        userService.registerUser(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUid(623127121543303168L);
        user.setName("111222");
        boolean result = userService.updateUser(user);
    }

    @Test
    public void findUserByPage() {
        PageInfo<User> result = userService.findUserByPage(0, 5);
        Assert.assertNotNull(result);
    }

}