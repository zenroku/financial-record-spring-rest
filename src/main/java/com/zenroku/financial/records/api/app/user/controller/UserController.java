package com.zenroku.financial.records.api.app.user.controller;

import com.zenroku.financial.records.api.app.user.entity.User;
import com.zenroku.financial.records.api.settings.model.BaseController;
import com.zenroku.financial.records.api.settings.model.BaseResponse;

public interface UserController extends BaseController {
    BaseResponse create(User user) throws Exception;

    BaseResponse update(Long id, User user) throws Exception;
}
