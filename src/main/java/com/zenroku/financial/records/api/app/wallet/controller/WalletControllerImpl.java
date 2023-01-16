package com.zenroku.financial.records.api.app.wallet.controller;

import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.app.wallet.service.WalletService;
import com.zenroku.financial.records.api.settings.constant.Route;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Route.wallet)
public class WalletControllerImpl implements WalletController{

    @Autowired
    private WalletService walletService;
    @Override
    @PostMapping
    public BaseResponse create(@RequestBody Wallet wallet) throws Exception{
        return walletService.create(wallet);
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable("id") Long id,@RequestBody Wallet wallet) throws Exception{
        return walletService.update(id,wallet);
    }

    @Override
    @GetMapping
    public BaseResponseArray get() throws Exception {
        return walletService.get();
    }

    @Override
    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) throws Exception{
        return walletService.getById(id);
    }
}
