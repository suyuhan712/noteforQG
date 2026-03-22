package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户Mapper接口
 */
public interface UserMapper {
    
    /**
     * 根据用户ID查询用户
     */
    User selectByUserId(@Param("userId") String userId);
    
    /**
     * 根据用户ID和密码查询用户（登录）
     */
    User selectByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);
    
    /**
     * 查询所有用户
     */
    List<User> selectAll();
    
    /**
     * 插入用户
     */
    int insert(User user);
    
    /**
     * 更新用户密码
     */
    int updatePassword(@Param("userId") String userId, @Param("password") String password);
    
    /**
     * 更新用户宿舍信息
     */
    int updateDormitory(@Param("userId") String userId, @Param("building") String building, @Param("room") String room);
    
    /**
     * 更新用户信息
     */
    int update(User user);
}