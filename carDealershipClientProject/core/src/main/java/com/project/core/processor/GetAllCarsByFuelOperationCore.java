package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.FuelRequest;
import com.project.api.operation.GetAllCarsByFuelOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByFuelOperationCore implements GetAllCarsByFuelOperation {
    // returns all available cars by selected fuel
    private final GetAllCarsByFuel getAllCarsByFuel;

    public GetAllCarsByFuelOperationCore(GetAllCarsByFuel getAllCarsByFuel) {
        this.getAllCarsByFuel = getAllCarsByFuel;
    }

    @Override
    public Either<Error, CarListResponse> process(final FuelRequest input) {
        return Try.of(() -> {
            return getAllCarsByFuel.getAllCarsByFuel(input.getFuel());
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            return new CarNotFoundError();
        });
    }
}
