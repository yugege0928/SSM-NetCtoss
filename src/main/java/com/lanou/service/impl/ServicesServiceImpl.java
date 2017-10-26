package com.lanou.service.impl;


import com.lanou.bean.Servicee;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServicesService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/25.
 */
@Service
public class ServicesServiceImpl implements ServicesService {

    @Resource
    private ServiceMapper mapper;

    @Override
    public List<Servicee> findAllServices() {

        return mapper.findAllServices();
    }

    @Override
    public int addService(Servicee service) {
        return mapper.insertSelective(service);
    }

    @Override
    public Servicee findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updataService(Servicee servicee) {
        return mapper.updateByPrimaryKeySelective(servicee);
    }

    @Override
    public int deleteServicee(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }


}
