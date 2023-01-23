package com.zenroku.financial.records.api.settings.model;

import javax.servlet.http.HttpServletRequest;

public interface BaseController {
    BaseResponseArray get(HttpServletRequest servletRequest, BaseRequest request) throws Exception;
    BaseResponse getById(Long id) throws Exception;
}
