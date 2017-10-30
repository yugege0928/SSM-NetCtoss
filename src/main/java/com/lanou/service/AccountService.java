package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;

import java.util.List;

/**
 * Created by yugege on 17/10/23.
 */
public interface AccountService {
    List<Account> findAllAccount();

    int deleteAccountById(Integer id);

    int insertAccount(Account account);

    int updateAccount(Account account);

    Account findById(Integer id);

    List<Account> findWithPageInfo(Integer pageNo,Integer pageSize);

    PageInfo<Account> getPageInfo(Integer pageSize);

    Account findByIDCard(String id);

    List<Account> findByCon(Account account);
}
