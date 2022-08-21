package com.project.api.error;

import com.project.api.base.Error;
import org.springframework.http.HttpStatus;

public class NoCarsForCriteriaError implements Error {
    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "No cars matching given criteria";
    }
}
