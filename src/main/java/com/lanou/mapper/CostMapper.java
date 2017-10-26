package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    //插入数据
    int insertSelective(Cost record);

    //根据ID查找资费
    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    //查找全部
    List<Cost> findAllCost();

    Cost findByType(String type);

    //升序查询
    List<Cost> findByBaseCostAsc();
    List<Cost> findByByBaseDurationAsc();

    //降序查询
    List<Cost> findByBaseCostDesc();
    List<Cost> findByByBaseDurationDesc();

}