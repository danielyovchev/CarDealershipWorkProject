package com.project.api.error;

import com.project.api.base.Error;
import org.springframework.http.HttpStatus;

public class CarNotAvailableError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

    @Override
    public String getMessage() {
        return "The car is unavailable for sell";
    }
}
