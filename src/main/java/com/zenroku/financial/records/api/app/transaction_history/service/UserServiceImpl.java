package com.zenroku.financial.records.api.app.transaction_history.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;
    @Override
    public BaseResponseArray get() {
        BaseResponseArray response = new BaseResponseArray();
        List<User> userList = userRepository.findAll();
        response.setDatas(userList);
        return response;
    }

    @Override
    public BaseResponse create(User user) {
        BaseResponse response = new BaseResponse();
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        if (validate.isEmpty()){
            Map<String,Object> mapUser = objectMapper.convertValue(userRepository.save(user),Map.class);
            response.setData(mapUser);
        } else {
            response.setSuccess(false);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
        }
        return response;
    }

    @Override
    public BaseResponse update(Long id, User user) {
        return null;
    }

    @Override
    public BaseResponse getById(Long id) {
        return null;
    }
}
