package com.zenroku.financial.records.api.app.userwallet.controller;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseController;
import com.zenroku.financial.records.api.settings.model.BaseResponse;

public interface UserWalletController extends BaseController {
    BaseResponse create(UserWallet user) throws Exception;

    BaseResponse update(Long id, UserWallet user) throws Exception;
}
