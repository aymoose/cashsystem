package com.mos.service;

import com.mos.dao.AccountDao;
import com.mos.entity.Account;

import java.util.List;


public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public boolean register(Account account) {
        return this.accountDao.register(account);
    }

    public Account login(String username, String password ) {
        return this.accountDao.login(username,password);
    }

    public List<Account> queryAllAccount(){
        return this.accountDao.queryAccount();
    }

}