package com.lanou.mapper;

import com.lanou.bean.Servicee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Servicee record);

    int insertSelective(Servicee record);

    Servicee selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Servicee record);

    int updateByPrimaryKey(Servicee record);

    List<Servicee> findAllServices();


    /**
     * 模糊查询
     */
    List<Servicee> selectServiceByTj(@Param("osUsername") String osUsername,
                                     @Param("unixHost") String unixHost,
                                     @Param("idcardNo") String idcardNo);
}