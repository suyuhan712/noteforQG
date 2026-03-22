package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.apache.ibatis.session.SqlSession;

/**
 * 用户服务类
 */
public class UserService {
    private SqlSession sqlSession;
    
    public UserService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    private UserMapper getUserMapper() {
        return sqlSession.getMapper(UserMapper.class);
    }
    
    /**
     * 用户注册
     */
    public boolean register(String userId, String password, String role) {
        UserMapper userMapper = getUserMapper();
        
        // 检查用户是否已存在
        User existingUser = userMapper.selectByUserId(userId);
        if (existingUser != null) {
            return false;
        }
        
        // 创建新用户
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setRole(role);
        
        return userMapper.insert(user) > 0;
    }
    
    /**
     * 用户登录
     */
    public User login(String userId, String password) {
        return getUserMapper().selectByUserIdAndPassword(userId, password);
    }
    
    /**
     * 修改密码
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        UserMapper userMapper = getUserMapper();
        User user = userMapper.selectByUserIdAndPassword(userId, oldPassword);
        
        if (user == null) {
            return false;
        }
        
        return userMapper.updatePassword(userId, newPassword) > 0;
    }
    
    /**
     * 绑定宿舍信息
     */
    public boolean bindDormitory(String userId, String building, String room) {
        UserMapper userMapper = getUserMapper();
        int result = userMapper.updateDormitory(userId, building, room);
        return result > 0;
    }
    
    /**
     * 获取用户信息
     */
    public User getUserInfo(String userId) {
        return getUserMapper().selectByUserId(userId);
    }
}