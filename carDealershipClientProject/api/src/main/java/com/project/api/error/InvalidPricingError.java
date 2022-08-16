package com.project.api.error;

import com.project.api.base.Error;
import org.springframework.http.HttpStatus;

public class InvalidPricingError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "Price calculation not successful";
    }
}
