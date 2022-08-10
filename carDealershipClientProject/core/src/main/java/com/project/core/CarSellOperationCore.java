package com.project.core;

import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import io.vavr.control.Either;

public class CarSellOperationCore implements CarSellOperation {
    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        return null;
    }
}
