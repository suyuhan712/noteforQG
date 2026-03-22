package com.example.pojo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 报修单实体类
 */
@Data
public class RepairOrder {
    private Integer id;
    private String userId;
    private String deviceType;
    private String description;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}