package com.project.api.operation;

import com.project.api.base.OperationProcessor;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import io.vavr.control.Either;

public interface CarSellOperation extends OperationProcessor<CarSellRequest, CarSellResponse> {
    @Override
    Either<Error, CarSellResponse> process(CarSellRequest carSellRequest);
}
