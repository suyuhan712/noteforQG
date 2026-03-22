package com.example;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyBatisTest {

    // 测试新增用户
    @Test
    public void testInsertUser() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            
            // 创建用户
            User user = new User();
            user.setUserId("3125999999");
            user.setPassword("test123");
            user.setRole("student");
            
            // 执行插入
            int result = mapper.insert(user);
            
            // 验证结果
            if (result > 0) {
                System.out.println("新增成功！影响行数：" + result);
            }
        }
    }

    // 测试查询所有用户
    @Test
    public void testSelectAll() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> userList = mapper.selectAll();
            
            // 遍历输出
            for (User user : userList) {
                System.out.println("用户ID: " + user.getUserId() + 
                                   ", 角色: " + user.getRole() + 
                                   ", 宿舍: " + user.getBuilding() + user.getRoom());
            }
        }
    }
    
    // 测试根据用户ID查询
    @Test
    public void testSelectByUserId() {
        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectByUserId("3125004123");
            
            if (user != null) {
                System.out.println("用户信息: " + user);
            } else {
                System.out.println("用户不存在");
            }
        }
    }
}