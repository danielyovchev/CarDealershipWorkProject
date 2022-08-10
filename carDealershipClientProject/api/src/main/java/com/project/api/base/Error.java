package com.project.api.base;


import org.springframework.http.HttpStatus;

public interface Error {
    HttpStatus getCode();
    String getMessage();
}
