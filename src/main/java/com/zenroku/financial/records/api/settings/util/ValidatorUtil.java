package com.zenroku.financial.records.api.settings.util;

import com.zenroku.financial.records.api.settings.model.BaseResponse;
import javax.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorUtil {
    public static <T> void extractMessages(BaseResponse response, Set<ConstraintViolation<T>> validate){
        response.setSuccess(false);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
    }
}
