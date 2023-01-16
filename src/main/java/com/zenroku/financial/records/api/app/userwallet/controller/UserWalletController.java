package com.zenroku.financial.records.api.app.userwallet.controller;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;

public interface UserWalletController {
    BaseResponseArray get();

    BaseResponse create(UserWallet user);

    BaseResponse update(Long id, UserWallet user);

    BaseResponse getById(Long id);
}
