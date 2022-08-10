package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotAvailableError;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class CarSellOperationCore implements CarSellOperation {
    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        return Try.of(() -> {
            return CarSellResponse.builder().car("Ford").price(8500.60).build();
        }).toEither().mapLeft( Throwable -> {
            if(Throwable.getMessage().isEmpty()){
                return new CarNotFoundError();
            }
            return new CarNotAvailableError();
        });
    }
}
