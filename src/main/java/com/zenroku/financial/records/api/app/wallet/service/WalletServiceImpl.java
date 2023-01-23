package com.zenroku.financial.records.api.app.wallet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.app.wallet.repository.WalletRepository;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import com.zenroku.financial.records.api.settings.util.ValidatorUtil;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WalletServiceImpl implements WalletService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private Validator validator;
    @Override
    public BaseResponseArray get() {
        BaseResponseArray response = new BaseResponseArray();
        List<Wallet> walletList = walletRepository.findAll();
        response.setData(walletList);
        return response;
    }

    @Override
    public BaseResponse create(Wallet wallet) {
        BaseResponse response = new BaseResponse();
        Set<ConstraintViolation<Wallet>> validate = validator.validate(wallet);
        if (validate.isEmpty()){
            wallet.setName(wallet.getName().toUpperCase());
            Map<String,Object> mapWallet = objectMapper.convertValue(walletRepository.save(wallet),Map.class);
            response.setData(mapWallet);
        } else {
            ValidatorUtil.extractMessages(response,validate);
        }
        return response;
    }

    @Override
    public BaseResponse update(Long id, Wallet wallet) throws Exception{
        BaseResponse response = new BaseResponse();
        Wallet updateWallet = walletRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Wallet not found with id " + id));

        Set<ConstraintViolation<Wallet>> validate = validator.validate(wallet);
        if (validate.isEmpty()){
            updateWallet.setName(wallet.getName().toUpperCase());
            Map<String,Object> mapUser = objectMapper.convertValue(walletRepository.save(updateWallet),Map.class);
            response.setData(mapUser);
        } else {
            ValidatorUtil.extractMessages(response,validate);
        }

        return response;
    }

    @Override
    public BaseResponse getById(Long id) throws Exception {
        BaseResponse response = new BaseResponse();
        Wallet getWallet = walletRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Wallet not found with id " + id));;

        Map<String,Object> mapUser = objectMapper.convertValue(getWallet,Map.class);
        response.setData(mapUser);

        return response;
    }
}
