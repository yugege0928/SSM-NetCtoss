package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/26.
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper mapper;

    @Override
    public List<RoleInfo> findAllRole() {
        return mapper.selectAllRoleInfo();
    }

    /**
     * 分页显示全部
     * @param no
     * @param pagesize
     * @return
     */
    public PageInfo<RoleInfo> findRolePageInfo(Integer no,Integer pagesize){
        PageInfo<RoleInfo> pageInfo = getPageInfo(no,pagesize);
        return pageInfo;
    }

    /**
     * 通过ID查找role用户
     * @param roleId
     * @return
     */
    @Override
    public RoleInfo findRoleByRoleID(Integer roleId) {
        return mapper.selectByPrimaryKey(roleId);
    }

    /**
     * 更新
     * @param role
     */
    @Override
    public void updateRole(RoleInfo role) {
        mapper.updateByRole(role.getName(),role.getRoleId());
        mapper.deleteByPrimaryKey(role.getRoleId());
        for (ModuleInfo module : role.getModuleInfoList()){
            mapper.updaRoleModule(role.getRoleId(),module.getModuleId());
        }
        System.out.println("role.getModuleInfoList():---"+role.getModuleInfoList());
    }

    @Override
    public void addRole(RoleInfo role) {
        mapper.insertRole(role);
        Integer roleId = role.getRoleId();
        for (ModuleInfo module : role.getModuleInfoList()){
            mapper.updaRoleModule(roleId,module.getModuleId());
        }
    }

    @Override
    public void deleteRole(RoleInfo roleInfo) {

        mapper.deleteByPrimaryKey(roleInfo.getRoleId());
        mapper.deleterRole(roleInfo.getRoleId());
    }

    public PageInfo<RoleInfo> getPageInfo(Integer no,Integer pagesize){
        no = no ==null ? 1 : no;
        pagesize = pagesize == null ? 5 : pagesize;
        PageHelper.startPage(no,pagesize);
        List<RoleInfo> allRole = mapper.selectAllRoleInfo();
        PageInfo<RoleInfo> pageInfo = new PageInfo<>(allRole);
        return pageInfo;
    }


}
