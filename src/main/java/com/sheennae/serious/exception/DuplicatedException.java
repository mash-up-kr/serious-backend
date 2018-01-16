package com.sheennae.serious.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicatedException extends BaseException {

    public DuplicatedException(String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }
}
