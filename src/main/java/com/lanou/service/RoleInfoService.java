package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.RoleInfo;

import java.util.List;

/**
 * Created by yugege on 17/10/26.
 */
public interface RoleInfoService {
    List<RoleInfo> findAllRole();

    PageInfo<RoleInfo> findRolePageInfo(Integer no,Integer pagesize);

    RoleInfo findRoleByRoleID(Integer roleId);

    void updateRole(RoleInfo role);

    void addRole(RoleInfo role);

    void deleteRole(RoleInfo roleInfo);
}
