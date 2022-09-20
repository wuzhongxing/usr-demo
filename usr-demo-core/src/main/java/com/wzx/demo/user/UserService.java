package com.wzx.demo.user;

import com.github.pagehelper.PageInfo;
import com.wzx.demo.entity.User;

import java.util.List;

/**
 * 用户业务操作服务
 */
public interface UserService {
    /**
     * 用户查询
     *
     * @param uid
     * @return
     */
    User getUser(Long uid);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    Long registerUser(User user);

    /**
     * 用户修改
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 用户删除
     *
     * @param uidList
     * @return
     */
    boolean delUser(List<Long> uidList);

    PageInfo<User> findUserByPage(int pageNum, int pageSize);
}
