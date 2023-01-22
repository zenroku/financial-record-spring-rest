package com.zenroku.financial.records.api.app.transactionhistory.controller;

import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.app.transactionhistory.service.TransactionHistoryService;
import com.zenroku.financial.records.api.settings.constant.Route;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(Route.transactionHistory)
public class TransactionHistoryControllerImpl implements TransactionHistoryController{

    @Autowired
    TransactionHistoryService transactionHistoryService;
    @Override
    @PostMapping
    public BaseResponse create(@RequestBody TransactionHistory history) throws Exception{
        return transactionHistoryService.create(history);
    }

    @DeleteMapping
    @Override
    public BaseResponse delete(Long id) {
        return null;
    }

    @Override
    public BaseResponseArray get(HttpServletRequest request) throws Exception {
        return null;
    }

    @Override
    public BaseResponse getById(Long id) throws Exception {
        return null;
    }
}
