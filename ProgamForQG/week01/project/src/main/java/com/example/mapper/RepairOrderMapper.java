package com.example.mapper;

import com.example.pojo.RepairOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报修单Mapper接口
 */
public interface RepairOrderMapper {
    
    /**
     * 创建报修单
     */
    int insert(RepairOrder repairOrder);
    
    /**
     * 根据学生ID查询报修单列表
     */
    List<RepairOrder> selectByUserId(@Param("userId") String userId);
    
    /**
     * 根据ID查询报修单
     */
    RepairOrder selectById(@Param("id") Integer id);
    
    /**
     * 查询所有报修单
     */
    List<RepairOrder> selectAll();
    
    /**
     * 根据状态查询报修单
     */
    List<RepairOrder> selectByStatus(@Param("status") String status);
    
    /**
     * 更新报修单状态
     */
    int updateStatus(@Param("id") Integer id, @Param("status") String status);
    
    /**
     * 删除报修单
     */
    int deleteById(@Param("id") Integer id);
}