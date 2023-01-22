package com.zenroku.financial.records.api.app.userwallet.service;

import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseService;

public interface UserWalletService  extends BaseService {
    BaseResponse create(UserWallet userWallet) throws Exception;

    BaseResponse update(Long id, UserWallet userWallet) throws Exception;
}
