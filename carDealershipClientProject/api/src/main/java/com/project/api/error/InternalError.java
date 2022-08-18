package com.project.api.error;

import com.project.api.base.Error;
import org.springframework.http.HttpStatus;

public class InternalError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Internal Server error";
    }
}
