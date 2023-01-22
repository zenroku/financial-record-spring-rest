package com.zenroku.financial.records.api.app.wallet.service;

import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseService;

public interface WalletService extends BaseService {
    BaseResponse create(Wallet wallet) throws Exception;

    BaseResponse update(Long id, Wallet wallet) throws Exception;
}
