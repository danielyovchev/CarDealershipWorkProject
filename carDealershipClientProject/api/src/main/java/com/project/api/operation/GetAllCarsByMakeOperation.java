package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.CarMakeRequest;
import io.vavr.control.Either;

public interface GetAllCarsByMakeOperation extends OperationProcessor<CarMakeRequest, CarListResponse> {
    @Override
    Either<Error, CarListResponse> process(CarMakeRequest input);
}
