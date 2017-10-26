package com.lanou.mapper;

import com.lanou.bean.Servicee;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Servicee record);

    int insertSelective(Servicee record);

    Servicee selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Servicee record);

    int updateByPrimaryKey(Servicee record);

    List<Servicee> findAllServices();


}