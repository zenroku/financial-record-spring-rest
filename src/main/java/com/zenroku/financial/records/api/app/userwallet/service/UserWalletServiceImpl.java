package com.zenroku.financial.records.api.app.userwallet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.app.userwallet.entity.UserWallet;
import com.zenroku.financial.records.api.app.userwallet.repository.UserWalletRepository;
import com.zenroku.financial.records.api.app.wallet.entity.Wallet;
import com.zenroku.financial.records.api.app.wallet.repository.WalletRepository;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import com.zenroku.financial.records.api.settings.util.DataNotFound;
import com.zenroku.financial.records.api.settings.util.ValidatorUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public BaseResponse create(UserWallet userWallet) {
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
    public BaseResponse update(Long id, UserWallet userWallet) {
        BaseResponse response = new BaseResponse();

        Optional<UserWallet> optionalUserWallet = userWalletRepository.findById(id);

        if (optionalUserWallet.isPresent()){
            UserWallet updatedUserWallet = optionalUserWallet.get();
            updatedUserWallet.setBalance(userWallet.getBalance());

            Set<ConstraintViolation<UserWallet>> validate = validator.validate(updatedUserWallet);
            if (validate.isEmpty()){
                Map<String,Object> mapUser = objectMapper.convertValue(userWalletRepository.save(updatedUserWallet),Map.class);
                response.setData(mapUser);
            } else {
                ValidatorUtil.extractMessages(response,validate);
            }

        } else {
            DataNotFound.baseResponse(response,"User Wallet",id);
        }

        return response;
    }

    @Override
    public BaseResponse getById(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<UserWallet> optionalUserWallet = userWalletRepository.findById(id);
        if (optionalUserWallet.isEmpty()){
            DataNotFound.baseResponse(response,"User Wallet",id);
        } else {
            Map<String,Object> mapUser = objectMapper.convertValue(optionalUserWallet.get(),Map.class);
            response.setData(mapUser);
        }
        return response;
    }

    private void extractRelations(UserWallet userWallet, BaseResponse response){
        Optional<User> userCheck = userRepository.findById(userWallet.getUserId());
        if (userCheck.isEmpty()) DataNotFound.baseResponse(response,"User", userWallet.getUserId());
        Optional<Wallet> walletCheck = walletRepository.findById(userWallet.getWalletId());
        if (walletCheck.isEmpty()) DataNotFound.baseResponse(response,"Wallet", userWallet.getWalletId());

        if (response.getSuccess()){
            userWallet.setUserRelations(userCheck.get());
            userWallet.setWalletRelations(walletCheck.get());
        }
    }
}
