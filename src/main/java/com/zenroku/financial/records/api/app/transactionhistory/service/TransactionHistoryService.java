package com.zenroku.financial.records.api.app.transactionhistory.service;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseService;

public interface TransactionHistoryService extends BaseService {
    BaseResponse create(User user);

    BaseResponse update(Long id, User user) throws DataNotFoundException;
}
