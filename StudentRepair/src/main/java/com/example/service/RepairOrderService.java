package com.example.service;

import com.example.mapper.RepairOrderMapper;
import com.example.pojo.RepairOrder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 报修单服务类
 */
public class RepairOrderService {
    private SqlSession sqlSession;
    
    public RepairOrderService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    private RepairOrderMapper getRepairOrderMapper() {
        return sqlSession.getMapper(RepairOrderMapper.class);
    }
    
    /**
     * 创建报修单
     */
    public boolean createRepairOrder(String userId, String deviceType, String description) {
        RepairOrder order = new RepairOrder();
        order.setUserId(userId);
        order.setDeviceType(deviceType);
        order.setDescription(description);
        order.setStatus("待处理");
        
        return getRepairOrderMapper().insert(order) > 0;
    }
    
    /**
     * 查看学生的报修记录
     */
    public List<RepairOrder> getStudentRepairOrders(String userId) {
        return getRepairOrderMapper().selectByUserId(userId);
    }
    
    /**
     * 查看报修单详情
     */
    public RepairOrder getRepairOrderDetail(Integer id) {
        return getRepairOrderMapper().selectById(id);
    }
    
    /**
     * 查询所有报修单
     */
    public List<RepairOrder> getAllRepairOrders() {
        return getRepairOrderMapper().selectAll();
    }
    
    /**
     * 根据状态查询报修单
     */
    public List<RepairOrder> getRepairOrdersByStatus(String status) {
        return getRepairOrderMapper().selectByStatus(status);
    }
    
    /**
     * 更新报修单状态
     */
    public boolean updateRepairOrderStatus(Integer id, String status) {
        return getRepairOrderMapper().updateStatus(id, status) > 0;
    }
    
    /**
     * 取消报修单
     */
    public boolean cancelRepairOrder(Integer id) {
        return getRepairOrderMapper().updateStatus(id, "已取消") > 0;
    }
    
    /**
     * 删除报修单
     */
    public boolean deleteRepairOrder(Integer id) {
        return getRepairOrderMapper().deleteById(id) > 0;
    }
}