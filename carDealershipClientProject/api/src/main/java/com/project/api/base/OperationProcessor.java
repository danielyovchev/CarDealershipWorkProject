package com.project.api.base;

import io.vavr.control.Either;

public interface OperationProcessor<I extends  OperationInput, R extends OperationResult> {
    Either<java.lang.Error, R> process(I input);
}
