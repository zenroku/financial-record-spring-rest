package com.zenroku.financial.records.api.app.transactionhistory.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.transactionhistory.entity.TransactionHistory;
import com.zenroku.financial.records.api.app.transactionhistory.model.ActionType;
import com.zenroku.financial.records.api.app.transactionhistory.repository.TransactionHistoryRepository;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.repository.UserWalletRepository;
import com.zenroku.financial.records.api.settings.exception.ApiException;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import com.zenroku.financial.records.api.settings.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    UserWalletRepository userWalletRepository;

    @Autowired
    Validator validator;

    @Transactional
    @Override
    public BaseResponse create(TransactionHistory history) throws Exception {
        BaseResponse response = new BaseResponse();
        Set<ConstraintViolation<TransactionHistory>> validate = validator.validate(history);
        if (validate.isEmpty()){
            UserWallet userWallet = userWalletRepository.findById(history.getUserWalletId())
                    .orElseThrow(()-> new DataNotFoundException("userWallet is not found"));

            BigDecimal currentBalance = userWallet.getBalance();
            BigDecimal actionBalance = history.getActionBalance();
            ActionType actionType = history.getActionType();

            if (history.getActionType().equals(ActionType.CREDIT) && currentBalance.compareTo(actionBalance) < 0){
                throw new ApiException("invalid credit action, insufficient userWalletBalance compare to actionBalance");
            }

            TransactionHistory newHistory = new TransactionHistory();
            newHistory.setCurrentBalance(currentBalance);
            newHistory.setActionBalance(actionBalance);
            newHistory.setDescription(history.getDescription());
            newHistory.setActionType(history.getActionType());
            newHistory.setTransactionDatetime(history.getTransactionDatetime() != null ? history.getTransactionDatetime() : LocalDateTime.now());

            BigDecimal resultBalance = null;

            if (actionType.equals(ActionType.DEBIT)){
                resultBalance = currentBalance.add(actionBalance);
            } else if (actionType.equals(ActionType.CREDIT)){
                resultBalance = currentBalance.subtract(actionBalance);
            }

            userWallet.setBalance(resultBalance);
            userWallet = userWalletRepository.save(userWallet);
            newHistory.setUserWalletRelations(userWallet);
            newHistory.setResultBalance(resultBalance);

            response.setData(new ObjectMapper().convertValue(
                    transactionHistoryRepository.save(newHistory),
                    Map.class)
            );

        } else {
            ValidatorUtil.extractMessages(response,validate);
        }

        return response;
    }

    @Override
    public BaseResponseArray get(BaseRequest request) throws Exception {
        BaseResponseArray response = new BaseResponseArray();
        Page<TransactionHistory> page = transactionHistoryRepository.findAll(
                request.getPageable()
        );

        response.setData(page.getContent());

        response.setProperties(
                page.getPageable().getPageNumber(),
                page.getContent().size(),
                page.getTotalElements(),
                page.getTotalPages()
                );
        return response;
    }
}
