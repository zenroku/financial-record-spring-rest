package com.zenroku.financial.records.api.app.transactionhistory.controller;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.settings.model.BaseController;
import com.zenroku.financial.records.api.settings.model.BaseResponse;

public interface TransactionHistoryController extends BaseController {

    BaseResponse create(TransactionHistory history) throws Exception;

    BaseResponse delete(Long id);
}
