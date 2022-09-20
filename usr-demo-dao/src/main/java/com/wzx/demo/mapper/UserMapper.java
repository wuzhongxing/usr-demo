package com.wzx.demo.mapper;

import com.wzx.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUid(Long id);

    int updateByUidSelective(User record);

    int delUsers(@Param("uidList") List<Long> uidList);

    int updateByPrimaryKey(User record);

    List<User> queryUserInfo();

}