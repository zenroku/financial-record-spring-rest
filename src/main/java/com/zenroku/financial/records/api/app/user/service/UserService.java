package com.zenroku.financial.records.api.app.user.service;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.settings.model.BaseResponse;
import com.zenroku.financial.records.api.settings.model.BaseResponseArray;

public interface UserService {

    BaseResponseArray get();

    BaseResponse create(User user);

    BaseResponse update(Long id, User user);

    BaseResponse getById(Long id);
}
