package com.example.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
public class User {
    private Integer id;
    private String userId;
    private String password;
    private String role;
    private String building;
    private String room;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}