package com.lanou.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
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

    //分页
    public List<Servicee> findWithPageInfo(Integer pageNo, Integer pageSize) {

        //目标:pageInfo对象,
        PageInfo<Servicee> pageInfo = queryCostByPage(pageNo,pageSize);

        return pageInfo.getList();
    }


    //获取分页信息
    public PageInfo<Servicee> getPageInfo(Integer pageSize) {

        return queryCostByPage(null,pageSize);
    }

    @Override
    public List<Servicee> findByTj(String osUsername, String unixHost,String idcardNo) {
        return mapper.selectServiceByTj(osUsername, unixHost,idcardNo);
    }

    public PageInfo<Servicee> queryCostByPage(Integer pageNo,Integer pageSize){

        //判断参数的合法性
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?3:pageSize;

        PageHelper.startPage(pageNo,pageSize);

        //获取全部的学员
        List<Servicee> studentList = mapper.findAllServices();

        //使用PageInfo对结果进行包装
        PageInfo<Servicee> pageInfo = new PageInfo<Servicee>(studentList);

//        System.out.println(pageInfo);

        return pageInfo;
    }

}
