package com.open.hotel.service.impl;

import com.open.hotel.AccountMapper;
import com.open.hotel.model.Account;
import com.open.hotel.model.Code;
import com.open.hotel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Account> accounts(String search) {
        return accountMapper.accounts(search);
    }

    @Override
    public List<Account> login(String account, String pwd) {
        return accountMapper.login(account,pwd);
    }

    @Override
    public List<Code> getCode(String tel) {
        accountMapper.initCode(tel,((int)(Math.random() * 10000))+"");
        return accountMapper.getCode(tel);
    }

    @Override
    public List<Account> loginByTel(String tel, String code) {
        List<Code> codes = accountMapper.checkCode(tel, code);
        if (null != codes && codes.size() > 0){
            List<Account> accounts = accountMapper.loginByTel(tel);
            if (null != accounts && accounts.size() > 0){
                return accounts;
            }
        }
        return null;
    }

    @Override
    public int delAcc(int id) {
        return accountMapper.delAcc(id);
    }

    @Override
    public int updateAcc(Account account) {
        if (accountMapper.checkExist(account.getId()) == null){
            return accountMapper.insertAcc(account.getAccount(),account.getName(),account.getTel(),account.getAge(),account.getSex(),account.getScore(),account.getPwd(),account.getEnable());
        }
        return accountMapper.updateAcc(account.getId(), account.getAccount(),account.getName(),account.getTel(),account.getAge(),account.getSex(),account.getScore(),account.getPwd(),account.getEnable());
    }
}
