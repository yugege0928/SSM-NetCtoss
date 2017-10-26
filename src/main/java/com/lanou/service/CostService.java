package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * Created by yugege on 17/10/20.
 */
public interface CostService {

    List<Cost> findAllCost();

    int deleteCostById(Integer id);

    int insertCost(Cost cost);

    int updateCost(Cost cost);

    Cost findById(Integer id);

    List<Cost> findWithPageInfo(Integer pageNo,Integer pageSize);

    PageInfo<Cost> getPageInfo(Integer pageSize);

    Cost findByType(String type);

}
