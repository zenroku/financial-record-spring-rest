package com.zenroku.financial.records.api.settings.model;

import com.zenroku.financial.records.api.settings.exception.DataNotFoundException;

public interface BaseController {
    BaseResponseArray get() throws Exception;
    BaseResponse getById(Long id) throws Exception;
}
