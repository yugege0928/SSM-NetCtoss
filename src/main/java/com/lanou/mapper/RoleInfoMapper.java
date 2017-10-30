package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> selectAllRoleInfo();


    void updateByRole(@Param("name") String name,
                      @Param("roleId") Integer roleId);


    //插入
    void updaRoleModule(@Param("roleId") Integer roleId,
                        @Param("moduleId") Integer moduleId);

    void insertRole(RoleInfo role);

    void deleterRole(@Param("roleId") Integer roleId);
}