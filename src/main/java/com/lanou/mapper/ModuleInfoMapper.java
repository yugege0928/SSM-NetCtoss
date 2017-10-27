package com.lanou.mapper;

import com.lanou.bean.ModuleInfo;

import java.util.List;

public interface ModuleInfoMapper {
    int insert(ModuleInfo record);

    int insertSelective(ModuleInfo record);

     List<ModuleInfo> findModuleByRoleId(Integer roleId);
}