package com.zenroku.financial.records.api.app.userwallet.controller;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.service.UserWalletService;
import com.zenroku.financial.records.api.settings.constant.Route;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(Route.userWallet)
public class UserWalletControllerImpl implements UserWalletController{
    @Autowired
    UserWalletService userWalletService;
    @Override
    @GetMapping
    public BaseResponseArray get(HttpServletRequest servletRequest, BaseRequest request) throws Exception {
        return userWalletService.get();
    }

    @Override
    @PostMapping
    public BaseResponse create(@RequestBody UserWallet userWallet) throws Exception {
        return userWalletService.create(userWallet);
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable("id") Long id, @RequestBody UserWallet userWallet) throws Exception {
        return userWalletService.update(id,userWallet);
    }

    @Override
    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) throws Exception {
        return userWalletService.getById(id);
    }
}
