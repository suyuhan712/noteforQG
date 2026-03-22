-- 用户表
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id VARCHAR(20) UNIQUE NOT NULL COMMENT '学号/工号',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    role VARCHAR(20) NOT NULL COMMENT '角色：student/admin',
    building VARCHAR(50) COMMENT '宿舍楼栋（学生）',
    room VARCHAR(50) COMMENT '房间号（学生）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 报修单表
CREATE TABLE repair_orders (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id VARCHAR(20) NOT NULL COMMENT '学生学号',
    device_type VARCHAR(50) NOT NULL COMMENT '设备类型',
    description TEXT NOT NULL COMMENT '问题描述',
    status VARCHAR(20) DEFAULT '待处理' COMMENT '状态：待处理/处理中/已完成/已取消',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报修单表';