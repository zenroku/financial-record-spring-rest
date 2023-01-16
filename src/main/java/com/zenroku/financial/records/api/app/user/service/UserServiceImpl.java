package com.zenroku.financial.records.api.app.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            validatedBaseResponse(response,validate);
        }
        return response;
    }

    @Override
    public BaseResponse update(Long id, User user) {
        BaseResponse response = new BaseResponse();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            userNotFound(response,id);
        } else {
            Set<ConstraintViolation<User>> validate = validator.validate(user);
            if (validate.isEmpty()){
                User updatedUser = optionalUser.get();
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setEmail(user.getEmail());
                Map<String,Object> mapUser = objectMapper.convertValue(userRepository.save(updatedUser),Map.class);
                response.setData(mapUser);
            } else {
                validatedBaseResponse(response,validate);
            }
        }

        return response;
    }

    @Override
    public BaseResponse getById(Long id) {
        BaseResponse response = new BaseResponse();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            userNotFound(response,id);
        } else {
            Map<String,Object> mapUser = objectMapper.convertValue(optionalUser.get(),Map.class);
            response.setData(mapUser);
        }
        return response;
    }

    void validatedBaseResponse(BaseResponse response, Set<ConstraintViolation<User>> validate){
        response.setSuccess(false);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
    }

    void userNotFound(BaseResponse response, Long id){
        response.setSuccess(false);
        response.setMessage("User not found with id " + id);
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
