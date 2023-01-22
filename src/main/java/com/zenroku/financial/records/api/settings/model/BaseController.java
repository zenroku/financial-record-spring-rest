package com.zenroku.financial.records.api.settings.model;

import javax.servlet.http.HttpServletRequest;

public interface BaseController {
    BaseResponseArray get(HttpServletRequest request) throws Exception;
    BaseResponse getById(Long id) throws Exception;
}
