package com.wzx.demo.user.impl;

import com.github.pagehelper.PageInfo;
import com.wzx.demo.BaseTest;
import com.wzx.demo.entity.User;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Objects;

public class UserServiceImplTest extends BaseTest {

    @Resource
    private UserServiceImpl userService;


    @Test
    public void saveUserTest() {
        User user = new User();
        user.setEmail(System.currentTimeMillis() + "@a.com");
        user.setName("测试" + System.currentTimeMillis());
        userService.registerUser(user);
    }

    @Test
    public void registerTest() {
        User user = new User();
        user.setEmail(System.currentTimeMillis() + "@a.com");
        user.setName("测试" + System.currentTimeMillis());
        userService.registerUser(user);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setUid(623127121543303168L);
        user.setName("111222");
        boolean result = userService.updateUser(user);
        Assert.assertTrue(result);
    }

    @Test
    public void delUserTest() {
        Long uid = 623127121543303168L;
        User user = userService.getUser(uid);
        if (Objects.nonNull(user)) {
            boolean result = userService.delUser(Lists.newArrayList(uid));
            Assert.assertTrue(result);
        }
    }

    @Test
    public void findUserByPageTest() {
        PageInfo<User> result = userService.findUserByPage(0, 5);
        Assert.assertNotNull(result);
    }

}