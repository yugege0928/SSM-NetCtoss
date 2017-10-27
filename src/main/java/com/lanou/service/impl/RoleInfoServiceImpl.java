package com.lanou.service.impl;

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



}
