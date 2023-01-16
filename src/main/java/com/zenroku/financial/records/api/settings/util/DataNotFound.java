package com.zenroku.financial.records.api.settings.util;

import com.zenroku.financial.records.api.settings.model.BaseResponse;
import org.springframework.http.HttpStatus;

public class DataNotFound {

    public static void baseResponse (BaseResponse response, String entityName, Long entityId){
        response.setSuccess(false);
        response.setMessage(entityName + " not found with id " + entityId);
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
