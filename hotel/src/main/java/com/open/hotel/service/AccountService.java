package com.open.hotel.service;

import com.open.hotel.model.Account;
import com.open.hotel.model.Code;

import java.util.List;

public interface AccountService {
    List<Account> accounts(String search);

    List<Account> login(String account, String pwd);

    List<Code> getCode(String tel);

    List<Account> loginByTel(String tel, String code);

    int delAcc(int id);

    int updateAcc(Account account);
}
