package com.zenroku.financial.records.api.settings.model;

public interface BaseController {
    BaseResponseArray get() throws Exception;
    BaseResponse getById(Long id) throws Exception;
}
