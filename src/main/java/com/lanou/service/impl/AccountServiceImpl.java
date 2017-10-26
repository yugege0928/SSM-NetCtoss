package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yugege on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper mapper;


    @Override
    public List<Account> findAllAccount() {
        return mapper.findAllAccount();
    }

    @Override
    public int deleteAccountById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertAccount(Account account) {
        return mapper.insertSelective(account);
    }

    @Override
    public int updateAccount(Account account) {
        return mapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public Account findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    //分页
    public List<Account> findWithPageInfo(Integer pageNo, Integer pageSize) {

        //目标:pageInfo对象,
        PageInfo<Account> pageInfo = queryCostByPage(pageNo,pageSize);

        return pageInfo.getList();
    }


    //获取分页信息
    public PageInfo<Account> getPageInfo(Integer pageSize) {

        return queryCostByPage(null,pageSize);
    }



    public PageInfo<Account> queryCostByPage(Integer pageNo,Integer pageSize){

        //判断参数的合法性
        pageNo = pageNo == null?1:pageNo;

        pageSize = pageSize == null?3:pageSize;

        PageHelper.startPage(pageNo,pageSize);

        //获取全部的学员
        List<Account> studentList = mapper.findAllAccount();

        //使用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(studentList);

//        System.out.println(pageInfo);

        return pageInfo;
    }

    @Override
    public Account findByIDCard(String id) {
        return mapper.findAccountByIDCard(id);
    }


}
