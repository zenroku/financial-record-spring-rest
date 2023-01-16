package com.zenroku.financial.records.api.app.userwallet.service;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;

public interface UserWalletService {
    BaseResponseArray get();

    BaseResponse create(UserWallet userWallet);

    BaseResponse update(Long id, UserWallet userWallet);

    BaseResponse getById(Long id);
}
