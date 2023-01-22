package com.zenroku.financial.records.api.settings.exception;

import com.zenroku.financial.records.api.settings.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Global {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex){
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        setResponse(response,status,ex);
        return new ResponseEntity<>(response,status);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> dataNotFoundExceptionHandler(Exception ex){
        BaseResponse response = new BaseResponse();
        HttpStatus status = HttpStatus.NOT_FOUND;
        setResponse(response,status,ex);

        return new ResponseEntity<>(response,status);
    }

    private void setResponse(BaseResponse response, HttpStatus status, Exception ex) {
        response.setSuccess(false);
        response.setStatus(status.value());
        response.setMessage(ex.getMessage());
    }
}
