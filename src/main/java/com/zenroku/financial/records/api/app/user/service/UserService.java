package com.zenroku.financial.records.api.app.user.service;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;
import com.zenroku.financial.records.api.settings.model.BaseRequest;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;

public interface UserService {

    BaseResponseArray get(BaseRequest request) throws Exception;

    BaseResponse create(User user);

    BaseResponse update(Long id, User user) throws DataNotFoundException;

    BaseResponse getById(Long id) throws DataNotFoundException;
}
