package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.CarColourRequest;
import com.project.api.model.carsByParam.CarListResponse;
import io.vavr.control.Either;

public interface GetAllCarsByColourOperation extends OperationProcessor<CarColourRequest, CarListResponse> {
    @Override
    Either<Error, CarListResponse> process(CarColourRequest input);
}
