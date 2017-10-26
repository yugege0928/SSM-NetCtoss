package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/20.
 */
@Service
public class CostServiceImpl implements CostService{

    @Resource
    private CostMapper mapper;

    public List<Cost> findAllCost() {

        List<Cost> list = mapper.findAllCost();

        return list;
    }

    public int deleteCostById(Integer id) {

        return mapper.deleteByPrimaryKey(id);
    }

    //插入数据
    public int insertCost(Cost cost) {
        return mapper.insertSelective(cost);
    }

    //更新数据
    public int updateCost(Cost cost) {

        return mapper.updateByPrimaryKeySelective(cost);
    }

    public Cost findById(Integer id) {

        Cost cost = mapper.selectByPrimaryKey(id);

        return cost;
    }

    //分页
    public List<Cost> findWithPageInfo(Integer pageNo, Integer pageSize) {

        //目标:pageInfo对象,
        PageInfo<Cost> pageInfo = queryCostByPage(pageNo,pageSize);

        return pageInfo.getList();
    }


    //获取分页信息
    public PageInfo<Cost> getPageInfo(Integer pageSize) {

        return queryCostByPage(null,pageSize);
    }



    public PageInfo<Cost> queryCostByPage(Integer pageNo,Integer pageSize){

        //判断参数的合法性
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?3:pageSize;

        PageHelper.startPage(pageNo,pageSize);

        //获取全部的学员
        List<Cost> studentList = mapper.findAllCost();

        //使用PageInfo对结果进行包装
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(studentList);

//        System.out.println(pageInfo);

        return pageInfo;
    }

    @Override
    public Cost findByType(String type) {
        return mapper.findByType(type);
    }


}
