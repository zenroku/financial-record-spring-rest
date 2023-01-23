package com.zenroku.financial.records.api.app.transactionhistory.service;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;

public interface TransactionHistoryService {
    BaseResponse create(TransactionHistory history) throws Exception;

    BaseResponseArray get(BaseRequest request) throws Exception;
}
