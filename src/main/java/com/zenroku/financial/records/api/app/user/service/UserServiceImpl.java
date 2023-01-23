package com.zenroku.financial.records.api.app.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.app.user.repository.UserRepository;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;
import com.zenroku.financial.records.api.settings.util.ValidatorUtil;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;
    @Override
    public BaseResponseArray get(BaseRequest request) throws Exception {
        BaseResponseArray response = new BaseResponseArray();
        Page<User> page = userRepository.findAll(request.getPageable());
        response.setData(page.getContent());

        response.setProperties(
                page.getPageable().getPageNumber(),
                page.getContent().size(),
                page.getTotalElements(),
                page.getTotalPages()
        );
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
            ValidatorUtil.extractMessages(response,validate);
        }
        return response;
    }

    @Override
    public BaseResponse update(Long id, User user) throws DataNotFoundException {
        BaseResponse response = new BaseResponse();
        User existUser = userRepository.findById(id).orElseThrow(()-> new DataNotFoundException("User not found with id " + id));

        Set<ConstraintViolation<User>> validate = validator.validate(existUser);
        if (validate.isEmpty()){
            existUser.setFirstName(existUser.getFirstName());
            existUser.setLastName(existUser.getLastName());
            existUser.setEmail(existUser.getEmail());
            Map<String,Object> mapUser = objectMapper.convertValue(userRepository.save(existUser),Map.class);
            response.setData(mapUser);
        } else {
            ValidatorUtil.extractMessages(response,validate);
        }

        return response;
    }

    @Override
    @Transactional
    public BaseResponse getById(Long id) throws DataNotFoundException {
        BaseResponse response = new BaseResponse();
        User getUser = userRepository.findById(id).orElseThrow(()-> new DataNotFoundException("User Not Found with id " + id));
        Map<String,Object> mapUser = objectMapper.convertValue(getUser,Map.class);
        response.setData(mapUser);

        return response;
    }
}
