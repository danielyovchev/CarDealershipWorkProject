package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carBuyModel.CarBuyRequest;
import com.project.api.model.carBuyModel.CarBuyResponse;
import io.vavr.control.Either;

public interface CarBuyOperation extends OperationProcessor<CarBuyRequest, CarBuyResponse> {
    @Override
    Either<Error, CarBuyResponse> process(CarBuyRequest input);
}
