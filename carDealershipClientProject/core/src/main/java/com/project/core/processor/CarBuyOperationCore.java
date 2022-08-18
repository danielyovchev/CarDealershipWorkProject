package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.model.carBuyModel.CarBuyRequest;
import com.project.api.model.carBuyModel.CarBuyResponse;
import com.project.api.operation.CarBuyOperation;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class CarBuyOperationCore implements CarBuyOperation {
    @Override
    public Either<Error, CarBuyResponse> process(CarBuyRequest input) {
        return null;
    }
}
