package com.zenroku.financial.records.api.app.userwallet.controller;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.service.UserWalletService;
import com.zenroku.financial.records.api.settings.constant.Route;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Route.userWallet)
public class UserWalletControllerImpl implements UserWalletController{
    @Autowired
    UserWalletService userWalletService;
    @Override
    @GetMapping
    public BaseResponseArray get() {
        return userWalletService.get();
    }

    @Override
    @PostMapping
    public BaseResponse create(@RequestBody UserWallet userWallet) {
        return userWalletService.create(userWallet);
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable("id") Long id, @RequestBody UserWallet userWallet) {
        return userWalletService.update(id,userWallet);
    }

    @Override
    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) {
        return userWalletService.getById(id);
    }
}
