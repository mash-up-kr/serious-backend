package com.sheennae.serious.exception;

import com.sheennae.serious.model.ErrorModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SeriousExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public @ResponseBody ErrorModel handleException(BaseException e) {
        return new ErrorModel(e.getStatusCode(), e.getMessage());
    }
}
