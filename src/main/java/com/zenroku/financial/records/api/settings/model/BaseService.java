package com.zenroku.financial.records.api.settings.model;

public interface BaseService {
    BaseResponseArray get(BaseRequest request) throws Exception;

    BaseResponse getById(Long id) throws Exception;
}
