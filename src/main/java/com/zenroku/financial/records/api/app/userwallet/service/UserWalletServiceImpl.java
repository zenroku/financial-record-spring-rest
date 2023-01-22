package com.zenroku.financial.records.api.app.userwallet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.repository.UserWalletRepository;
import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.app.wallet.repository.WalletRepository;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import com.zenroku.financial.records.api.settings.util.ValidatorUtil;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserWalletServiceImpl implements UserWalletService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserWalletRepository userWalletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    Validator validator;

    @Override
    public BaseResponseArray get() {
        BaseResponseArray response = new BaseResponseArray();
        List<UserWallet> userWalletList = userWalletRepository.findAll();
        response.setDatas(userWalletList);
        return response;
    }

    @Override
    @Transactional
    public BaseResponse create(UserWallet userWallet) throws Exception{
        BaseResponse response = new BaseResponse();
        extractRelations(userWallet,response);
        if (response.getSuccess()){
            Set<ConstraintViolation<UserWallet>> validate = validator.validate(userWallet);
            if (validate.isEmpty()){
                Map<String,Object> mapUser = objectMapper.convertValue(userWalletRepository.save(userWallet),Map.class);
                response.setData(mapUser);
            } else {
                ValidatorUtil.extractMessages(response,validate);
            }
        }

        return response;
    }

    @Override
    public BaseResponse update(Long id, UserWallet userWallet) throws Exception{
        BaseResponse response = new BaseResponse();

        UserWallet updateUserWallet = userWalletRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("User wallet not found with id " + id));
        extractRelations(updateUserWallet,response);

        if (response.getSuccess()){
            updateUserWallet.setBalance(userWallet.getBalance());
            Set<ConstraintViolation<UserWallet>> validate = validator.validate(updateUserWallet);
            if (validate.isEmpty()){
                Map<String,Object> mapUser = objectMapper.convertValue(userWalletRepository.save(updateUserWallet),Map.class);
                response.setData(mapUser);
            } else {
                ValidatorUtil.extractMessages(response,validate);
            }
        }

        return response;
    }

    @Override
    public BaseResponse getById(Long id) throws Exception {
        BaseResponse response = new BaseResponse();
        UserWallet getUserWallet = userWalletRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("User wallet not found with id " + id));

        Map<String,Object> mapUser = objectMapper.convertValue(getUserWallet,Map.class);
        response.setData(mapUser);

        return response;
    }

    private void extractRelations(UserWallet userWallet, BaseResponse response) throws Exception{
        User userCheck = userRepository.findById(userWallet.getUserId())
                .orElseThrow(()-> new DataNotFoundException("User not found with id " + userWallet.getUserId()));

        Wallet walletCheck = walletRepository.findById(userWallet.getWalletId())
                .orElseThrow(()-> new DataNotFoundException("Wallet not found with id " + userWallet.getWalletId()));

        if (response.getSuccess()){
            userWallet.setUserRelations(userCheck);
            userWallet.setWalletRelations(walletCheck);
        }
    }
}
