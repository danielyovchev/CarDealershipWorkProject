package com.project.data.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Car already exists")
public class CarAlreadyExistsException extends RuntimeException{
}
