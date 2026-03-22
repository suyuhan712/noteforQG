package com.example;

import com.example.pojo.RepairOrder;
import com.example.pojo.User;
import com.example.service.RepairOrderService;
import com.example.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * 宿舍报修管理系统 - 主程序
 */
public class Main {
    private static SqlSession sqlSession;
    private static UserService userService;
    private static RepairOrderService repairOrderService;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        try {
            // 初始化MyBatis
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession(true); // 自动提交
            
            // 初始化服务和扫描器
            userService = new UserService(sqlSession);
            repairOrderService = new RepairOrderService(sqlSession);
            scanner = new Scanner(System.in);
            
            // 启动系统
            run();
            
        } catch (IOException e) {
            System.err.println("系统初始化失败: " + e.getMessage());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * 运行系统
     */
    private static void run() {
        while (true) {
            showMainMenu();
            int choice = readInt(1, 3);
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("感谢使用，再见！");
                    return;
            }
        }
    }
    
    /**
     * 显示主菜单
     */
    private static void showMainMenu() {
        System.out.println("===========================");
        System.out.println("🏠 宿舍报修管理系统");
        System.out.println("===========================");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.print("请选择操作（输入 1-3）：");
    }
    
    /**
     * 用户登录
     */
    private static void login() {
        System.out.println("\n===== 用户登录 =====");
        System.out.print("请输入账号：");
        String userId = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        
        User user = userService.login(userId, password);
        
        if (user == null) {
            System.out.println("登录失败！账号或密码错误。");
            return;
        }
        
        System.out.println("登录成功！角色：" + (user.getRole().equals("student") ? "学生" : "维修人员"));
        
        // 根据角色进入不同菜单
        if ("student".equals(user.getRole())) {
            studentMenu(user);
        } else if ("admin".equals(user.getRole())) {
            adminMenu(user);
        }
    }
    
    /**
     * 用户注册
     */
    private static void register() {
        System.out.println("\n===== 用户注册 =====");
        System.out.print("请选择角色（1-学生，2-维修人员）：");
        int roleChoice = readInt(1, 2);
        String role = roleChoice == 1 ? "student" : "admin";
        
        String userId;
        while (true) {
            if (role.equals("student")) {
                System.out.print("请输入学号（前缀3125或3225）：");
            } else {
                System.out.print("请输入工号（前缀0025）：");
            }
            userId = scanner.nextLine();
            
            // 验证学号/工号格式
            if (validateUserId(userId, role)) {
                break;
            }
            System.out.println("格式错误，请重新输入！");
        }
        
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        System.out.print("请确认密码：");
        String confirmPassword = scanner.nextLine();
        
        if (!password.equals(confirmPassword)) {
            System.out.println("两次密码不一致，注册失败！");
            return;
        }
        
        boolean success = userService.register(userId, password, role);
        if (success) {
            System.out.println("注册成功！请返回主界面登录。");
        } else {
            System.out.println("注册失败！该账号已存在。");
        }
    }
    
    /**
     * 学生菜单
     */
    private static void studentMenu(User user) {
        while (true) {
            // 检查是否需要绑定宿舍
            if (user.getBuilding() == null || user.getRoom() == null) {
                System.out.println("\n首次登录，请先绑定宿舍！");
                System.out.println("当前用户ID: " + user.getUserId());
                boolean success = bindDormitory(user.getUserId());
                if (success) {
                    // 重新获取用户信息
                    User updatedUser = userService.getUserInfo(user.getUserId());
                    if (updatedUser != null) {
                        user = updatedUser;
                    }
                } else {
                    System.out.println("绑定失败，请重试。");
                    continue;
                }
            }
            
            showStudentMenu();
            int choice = readInt(1, 6);
            
            switch (choice) {
                case 1:
                    boolean bindSuccess = bindDormitory(user.getUserId());
                    if (bindSuccess) {
                        User updatedUser = userService.getUserInfo(user.getUserId());
                        if (updatedUser != null) {
                            user = updatedUser;
                        }
                    }
                    break;
                case 2:
                    createRepairOrder(user);
                    break;
                case 3:
                    viewMyRepairOrders(user);
                    break;
                case 4:
                    cancelRepairOrder();
                    break;
                case 5:
                    changePassword(user);
                    break;
                case 6:
                    System.out.println("退出学生菜单。");
                    return;
            }
        }
    }
    
    /**
     * 显示学生菜单
     */
    private static void showStudentMenu() {
        System.out.println("\n===== 学生菜单 =====");
        System.out.println("1. 绑定/修改宿舍");
        System.out.println("2. 创建报修单");
        System.out.println("3. 查看我的报修记录");
        System.out.println("4. 取消报修单");
        System.out.println("5. 修改密码");
        System.out.println("6. 退出");
        System.out.print("请选择操作（输入 1-6）：");
    }
    
    /**
     * 管理员菜单
     */
    private static void adminMenu(User user) {
        while (true) {
            showAdminMenu();
            int choice = readInt(1, 6);
            
            switch (choice) {
                case 1:
                    viewAllRepairOrders();
                    break;
                case 2:
                    viewRepairOrderDetail();
                    break;
                case 3:
                    updateRepairOrderStatus();
                    break;
                case 4:
                    deleteRepairOrder();
                    break;
                case 5:
                    changePassword(user);
                    break;
                case 6:
                    System.out.println("退出管理员菜单。");
                    return;
            }
        }
    }
    
    /**
     * 显示管理员菜单
     */
    private static void showAdminMenu() {
        System.out.println("\n===== 管理员菜单 =====");
        System.out.println("1. 查看所有报修单");
        System.out.println("2. 查看报修单详情");
        System.out.println("3. 更新报修单状态");
        System.out.println("4. 删除报修单");
        System.out.println("5. 修改密码");
        System.out.println("6. 退出");
        System.out.print("请选择操作（输入 1-6）：");
    }
    
    /**
     * 绑定宿舍
     */
    private static boolean bindDormitory(String userId) {
        System.out.println("\n===== 绑定宿舍 =====");
        System.out.print("请输入宿舍楼栋：");
        String building = scanner.nextLine();
        System.out.print("请输入房间号：");
        String room = scanner.nextLine();
        
        boolean success = userService.bindDormitory(userId, building, room);
        if (success) {
            System.out.println("宿舍绑定成功！");
        } else {
            System.out.println("宿舍绑定失败！");
        }
        return success;
    }
    
    /**
     * 创建报修单
     */
    private static void createRepairOrder(User user) {
        System.out.println("\n===== 创建报修单 =====");
        System.out.print("请输入设备类型：");
        String deviceType = scanner.nextLine();
        System.out.print("请输入问题描述：");
        String description = scanner.nextLine();
        
        boolean success = repairOrderService.createRepairOrder(user.getUserId(), deviceType, description);
        if (success) {
            System.out.println("报修单创建成功！");
        } else {
            System.out.println("报修单创建失败！");
        }
    }
    
    /**
     * 查看我的报修记录
     */
    private static void viewMyRepairOrders(User user) {
        System.out.println("\n===== 我的报修记录 =====");
        List<RepairOrder> orders = repairOrderService.getStudentRepairOrders(user.getUserId());
        
        if (orders.isEmpty()) {
            System.out.println("暂无报修记录。");
            return;
        }
        
        System.out.printf("%-5s %-10s %-15s %-20s %-15s\n", "ID", "学号", "设备类型", "状态", "创建时间");
        System.out.println("--------------------------------------------------------------------------------");
        for (RepairOrder order : orders) {
            System.out.printf("%-5d %-10s %-15s %-20s %-15s\n",
                    order.getId(),
                    order.getUserId(),
                    order.getDeviceType(),
                    order.getStatus(),
                    order.getCreateTime().toString().substring(0, 19));
        }
    }
    
    /**
     * 取消报修单
     */
    private static void cancelRepairOrder() {
        System.out.println("\n===== 取消报修单 =====");
        System.out.print("请输入报修单ID：");
        int id = readInt();
        
        RepairOrder order = repairOrderService.getRepairOrderDetail(id);
        if (order == null) {
            System.out.println("报修单不存在！");
            return;
        }
        
        if (!"待处理".equals(order.getStatus())) {
            System.out.println("只能取消待处理的报修单！");
            return;
        }
        
        boolean success = repairOrderService.cancelRepairOrder(id);
        if (success) {
            System.out.println("报修单已取消！");
        } else {
            System.out.println("取消失败！");
        }
    }
    
    /**
     * 查看所有报修单
     */
    private static void viewAllRepairOrders() {
        System.out.println("\n===== 查看报修单 =====");
        System.out.println("1. 查看所有报修单");
        System.out.println("2. 按状态筛选");
        System.out.print("请选择：");
        int choice = readInt(1, 2);
        
        List<RepairOrder> orders;
        if (choice == 1) {
            orders = repairOrderService.getAllRepairOrders();
        } else {
            System.out.println("请输入状态（待处理/处理中/已完成/已取消）：");
            String status = scanner.nextLine();
            orders = repairOrderService.getRepairOrdersByStatus(status);
        }
        
        if (orders.isEmpty()) {
            System.out.println("暂无报修单。");
            return;
        }
        
        System.out.printf("%-5s %-10s %-15s %-20s %-15s\n", "ID", "学号", "设备类型", "状态", "创建时间");
        System.out.println("--------------------------------------------------------------------------------");
        for (RepairOrder order : orders) {
            System.out.printf("%-5d %-10s %-15s %-20s %-15s\n",
                    order.getId(),
                    order.getUserId(),
                    order.getDeviceType(),
                    order.getStatus(),
                    order.getCreateTime().toString().substring(0, 19));
        }
    }
    
    /**
     * 查看报修单详情
     */
    private static void viewRepairOrderDetail() {
        System.out.println("\n===== 报修单详情 =====");
        System.out.print("请输入报修单ID：");
        int id = readInt();
        
        RepairOrder order = repairOrderService.getRepairOrderDetail(id);
        if (order == null) {
            System.out.println("报修单不存在！");
            return;
        }
        
        System.out.println("报修单ID：" + order.getId());
        System.out.println("学生学号：" + order.getUserId());
        System.out.println("设备类型：" + order.getDeviceType());
        System.out.println("问题描述：" + order.getDescription());
        System.out.println("状态：" + order.getStatus());
        System.out.println("创建时间：" + order.getCreateTime().toString().substring(0, 19));
        System.out.println("更新时间：" + order.getUpdateTime().toString().substring(0, 19));
    }
    
    /**
     * 更新报修单状态
     */
    private static void updateRepairOrderStatus() {
        System.out.println("\n===== 更新报修单状态 =====");
        System.out.print("请输入报修单ID：");
        int id = readInt();
        
        RepairOrder order = repairOrderService.getRepairOrderDetail(id);
        if (order == null) {
            System.out.println("报修单不存在！");
            return;
        }
        
        System.out.println("当前状态：" + order.getStatus());
        System.out.println("请选择新状态：");
        System.out.println("1. 待处理");
        System.out.println("2. 处理中");
        System.out.println("3. 已完成");
        System.out.print("请选择：");
        int choice = readInt(1, 3);
        
        String status;
        switch (choice) {
            case 1: status = "待处理"; break;
            case 2: status = "处理中"; break;
            case 3: status = "已完成"; break;
            default: status = "待处理";
        }
        
        boolean success = repairOrderService.updateRepairOrderStatus(id, status);
        if (success) {
            System.out.println("状态更新成功！");
        } else {
            System.out.println("状态更新失败！");
        }
    }
    
    /**
     * 删除报修单
     */
    private static void deleteRepairOrder() {
        System.out.println("\n===== 删除报修单 =====");
        System.out.print("请输入报修单ID：");
        int id = readInt();
        
        RepairOrder order = repairOrderService.getRepairOrderDetail(id);
        if (order == null) {
            System.out.println("报修单不存在！");
            return;
        }
        
        System.out.print("确认删除该报修单？（y/n）：");
        String confirm = scanner.nextLine();
        
        if ("y".equalsIgnoreCase(confirm) || "yes".equalsIgnoreCase(confirm)) {
            boolean success = repairOrderService.deleteRepairOrder(id);
            if (success) {
                System.out.println("报修单已删除！");
            } else {
                System.out.println("删除失败！");
            }
        } else {
            System.out.println("已取消删除。");
        }
    }
    
    /**
     * 修改密码
     */
    private static void changePassword(User user) {
        System.out.println("\n===== 修改密码 =====");
        System.out.print("请输入原密码：");
        String oldPassword = scanner.nextLine();
        System.out.print("请输入新密码：");
        String newPassword = scanner.nextLine();
        System.out.print("请确认新密码：");
        String confirmPassword = scanner.nextLine();
        
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("两次密码不一致！");
            return;
        }
        
        boolean success = userService.changePassword(user.getUserId(), oldPassword, newPassword);
        if (success) {
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码修改失败！原密码错误。");
        }
    }
    
    /**
     * 验证学号/工号格式
     */
    private static boolean validateUserId(String userId, String role) {
        if (userId == null || userId.length() != 10) {
            return false;
        }
        
        if (role.equals("student")) {
            return userId.startsWith("3125") || userId.startsWith("3225");
        } else if (role.equals("admin")) {
            return userId.startsWith("0025");
        }
        
        return false;
    }
    
    /**
     * 读取整数输入
     */
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("请输入有效的数字：");
            }
        }
    }
    
    /**
     * 读取范围内的整数
     */
    private static int readInt(int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("请输入%d-%d之间的数字：", min, max);
            } catch (NumberFormatException e) {
                System.out.print("请输入有效的数字：");
            }
        }
    }
}