package com.lanou.mapper;

import com.lanou.bean.Account;

import java.util.List;

public interface AccountMapper {
    int insert(Account record);

    int insertSelective(Account record);

    //查找所有账号
    List<Account> findAllAccount();

    //根据ID删除对应账号
    int deleteByPrimaryKey(Integer accountId);

    //根据ID查找对应账号
    Account selectByPrimaryKey(Integer accountId);

    //根据ID更新对应账号
    int updateByPrimaryKeySelective(Account account);

    //根据身份证查找对应账号
    Account findAccountByIDCard(String IDCard);

    List<Account> findByAll(Account account);

}