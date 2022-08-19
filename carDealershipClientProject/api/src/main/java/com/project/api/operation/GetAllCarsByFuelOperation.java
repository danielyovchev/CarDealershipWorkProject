package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.FuelRequest;
import io.vavr.control.Either;

public interface GetAllCarsByFuelOperation extends OperationProcessor<FuelRequest,CarListResponse> {
    @Override
    Either<Error, CarListResponse> process(FuelRequest input);
}
