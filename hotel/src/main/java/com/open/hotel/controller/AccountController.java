package com.open.hotel.controller;

import com.open.hotel.Response.Response;
import com.open.hotel.model.Account;
import com.open.hotel.model.Code;
import com.open.hotel.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@Api(tags = "账户表")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = {"/", "/{search}"})
    public Object accounts(@PathVariable(name = "search", required = false) String search) {
        System.out.println("search:" + search);
        if (null == search) {
            search = "";
        }
        List<Account> accounts = accountService.accounts(search);
        if (null != accounts && accounts.size() > 0) {
            return new Response().code(200).value(accounts);
        }
        return new Response().code(500).msg("暂无数据!").value(null);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Account account) {
        List<Account> login = accountService.login(account.getAccount(), account.getPwd());
        if (null == login || (login.size() < 1 || login.size() > 1)) {
            return new Response().code(500).msg("账号或密码错误!").value(null);
        } else {
            return new Response().code(200).msg("ok").value(login.get(0));
        }
    }

    @GetMapping("/getCode")
    public Object getCode(@RequestParam("tel") String tel) {
        List<Code> codes = accountService.getCode(tel);
        if (null == codes || codes.size() == 0) {
            return new Response().code(500).msg("获取验证码失败").value(null);
        } else {
            return new Response().code(200).msg("ok").value(codes.get(codes.size() - 1));
        }
    }

    @PostMapping("/loginByTel")
    public Object loginByTel(@RequestBody Code code) {
        List<Account> login = accountService.loginByTel(code.getTel(), code.getCode());
        if (null == login || (login.size() < 1 || login.size() > 1)) {
            return new Response().code(500).msg("账号或验证码错误!").value(null);
        } else {
            return new Response().code(200).msg("ok").value(login.get(0));
        }
    }

    @GetMapping("/delAcc")
    public Object delAcc(int id) {
        int del = accountService.delAcc(id);
        if (del == 1)
            return new Response().code(200).msg("OK").value(null);
        else
            return new Response().code(500).msg("fail").value(null);
    }

    @PostMapping("/updateAcc")
    public Object updateAcc(@RequestBody Account account) {
        int update = accountService.updateAcc(account);
        if (update == 1)
            return new Response().code(200).msg("OK").value(null);
        else
            return new Response().code(500).msg("fail").value(null);
    }
}
