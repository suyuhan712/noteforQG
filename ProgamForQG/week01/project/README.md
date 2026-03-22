# 宿舍报修管理系统

## 项目简介
这是一个基于Java + MyBatis + MySQL开发的宿舍报修管理系统，通过控制台进行交互。

## 技术栈
- Java 21
- MyBatis 3.5.16
- MySQL 8.0
- Maven
- Lombok

## 项目结构
```
StudentRepair/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── Main.java              # 主程序
│   │   │   ├── mapper/                # 数据访问层
│   │   │   │   ├── UserMapper.java
│   │   │   │   └── RepairOrderMapper.java
│   │   │   ├── pojo/                  # 实体类
│   │   │   │   ├── User.java
│   │   │   │   └── RepairOrder.java
│   │   │   └── service/               # 业务逻辑层
│   │   │       ├── UserService.java
│   │   │       └── RepairOrderService.java
│   │   └── resources/
│   │       ├── mybatis-config.xml     # MyBatis配置
│   │       ├── schema.sql             # 数据库建表脚本
│   │       └── mappers/               # SQL映射文件
│   │           ├── UserMapper.xml
│   │           └── RepairOrderMapper.xml
└── pom.xml                            # Maven配置
```

## 数据库配置

### 1. 创建数据库
```sql
CREATE DATABASE StudentRepair CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 执行建表脚本
```bash
mysql -uroot -psuyuhan666666 StudentRepair < src/main/resources/schema.sql
```

### 3. 数据库连接配置
配置文件：`src/main/resources/mybatis-config.xml`
- 数据库名：StudentRepair
- 用户名：root
- 密码：suyuhan666666

## 运行项目

### 方式一：使用Maven（推荐）
```bash
# 编译项目
mvn clean compile

# 运行项目
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### 方式二：使用IDE
1. 在IDE中打开项目
2. 确保已安装JDK 21
3. 配置Maven依赖（IDE会自动下载）
4. 运行Main.java

### 方式三：手动编译运行
```bash
# 编译
javac -cp "target/classes;target/dependency/*" -d target/classes src/main/java/com/example/*.java src/main/java/com/example/mapper/*.java src/main/java/com/example/pojo/*.java src/main/java/com/example/service/*.java

# 运行
java -cp "target/classes;target/dependency/*" com.example.Main
```

## 功能说明

### 公共功能
1. **用户注册** - 支持学生和管理员角色注册
2. **用户登录** - 学号/工号 + 密码登录
3. **修改密码** - 需要验证原密码
4. **查看基本信息** - 查看个人账户信息

### 学生功能
1. **绑定宿舍** - 首次登录必须绑定宿舍楼栋和房间号
2. **创建报修单** - 选择设备类型，填写问题描述
3. **查看报修记录** - 列表展示个人所有报修单
4. **取消报修单** - 只能取消"待处理"状态的报修单

### 管理员功能
1. **查询所有报修单** - 支持查看所有报修单或按状态筛选
2. **查看报修单详情** - 查看报修单的完整信息
3. **修改报修单状态** - 更新报修单状态（待处理/处理中/已完成）
4. **删除报修单** - 删除指定的报修单

## 用户规则

### 学生
- **学号格式**：10位数字，前缀为3125或3225
- 示例：3125004123、3225004123

### 管理员/维修人员
- **工号格式**：10位数字，前缀为0025
- 示例：0025004123

## 报修单状态
- **待处理** - 初始状态
- **处理中** - 维修人员开始处理
- **已完成** - 维修完成
- **已取消** - 学生取消

## 使用示例

### 1. 注册学生账号
```
请选择角色（1-学生，2-维修人员）：1
请输入学号（前缀3125或3225）：3125004123
请输入密码：test123
请确认密码：test123
注册成功！请返回主界面登录。
```

### 2. 登录系统
```
请输入账号：3125004123
请输入密码：test123
登录成功！角色：学生
```

### 3. 绑定宿舍
```
请输入宿舍楼栋：A栋
请输入房间号：301
宿舍绑定成功！
```

### 4. 创建报修单
```
请输入设备类型：空调
请输入问题描述：空调不制冷
报修单创建成功！
```

## 注意事项
1. 确保MySQL服务已启动
2. 确保数据库StudentRepair已创建
3. 首次运行需要Maven下载依赖，可能需要一些时间
4. 建议使用JDK 21或更高版本
5. 确保网络连接正常（用于下载Maven依赖）

## 开发者信息
- 项目名称：StudentRepair
- 开发语言：Java
- 数据库：MySQL
- 框架：MyBatis