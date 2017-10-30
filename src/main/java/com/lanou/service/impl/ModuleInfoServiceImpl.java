package com.lanou.service.impl;

import com.lanou.bean.ModuleInfo;
import com.lanou.mapper.ModuleInfoMapper;
import com.lanou.service.ModuleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/26.
 */
@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {

    @Resource
    private ModuleInfoMapper mapper;

    @Override
    public List<ModuleInfo> findAllModule() {
        return mapper.findAllMoudule();
    }
}
