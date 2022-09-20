package com.wzx.demo.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzx.demo.common.ApiException;
import com.wzx.demo.common.Snowflake;
import com.wzx.demo.entity.User;
import com.wzx.demo.mapper.UserMapper;
import com.wzx.demo.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.wzx.demo.common.CommonResponse.UID_ERROR;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final int WORK_ID = 1;
    private static final int PAGE_SIZE = 5;


    private Snowflake snowflake = Snowflake.create(WORK_ID);

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(Long uid) {
        if (Objects.isNull(uid)) {
            throw new ApiException(UID_ERROR.getCode(), UID_ERROR.getMessage());
        }
        return userMapper.selectByUid(uid);
    }

    @Override
    public Long registerUser(User user) {
        if (Objects.nonNull(user.getUid())) {
            throw new ApiException(UID_ERROR.getCode(), UID_ERROR.getMessage());
        }
        user.setUid(snowflake.nextId());
        try {
            userMapper.insertSelective(user);
        } catch (DuplicateKeyException e) {
            throw new ApiException(UID_ERROR.getCode(), e.getMessage());
        }
        log.info("user register success, uid:{}", user.getUid());
        return user.getUid();
    }

    @Override
    public boolean updateUser(User user) {
        if (Objects.isNull(user.getUid())) {
            throw new ApiException(UID_ERROR.getCode(), UID_ERROR.getMessage());
        }
        return userMapper.updateByUidSelective(user) == 1;
    }

    @Override
    public boolean delUser(List<Long> uidList) {
        if (CollectionUtils.isEmpty(uidList)) {
            throw new ApiException(UID_ERROR.getCode(), UID_ERROR.getMessage());
        }
        boolean result = userMapper.delUsers(uidList) > 0;
        log.info("user delete,userList:{} result:{}", uidList, result);
        return result;
    }

    @Override
    public PageInfo<User> findUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userMapper.queryUserInfo();
        PageInfo<User> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }
}
