package com.sheennae.serious.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException {

    public static final String MESSAGE = "Doesn't exist or wrong format uuid in header";

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED.value(), MESSAGE);
    }
}
