package com.zenroku.financial.records.api.app.wallet.controller;

import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.settings.model.BaseController;
import com.zenroku.financial.records.api.settings.model.BaseResponse;

public interface WalletController extends BaseController {
    BaseResponse create(Wallet wallet) throws Exception;

    BaseResponse update(Long id, Wallet wallet) throws Exception;
}
