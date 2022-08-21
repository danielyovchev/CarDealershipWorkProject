package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carsByParam.CarColourRequest;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.operation.GetAllCarsByColourOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByColourOperationCore implements GetAllCarsByColourOperation {
    private final GetAllCarsByMake getAllCarsByMake;

    public GetAllCarsByColourOperationCore(GetAllCarsByMake getAllCarsByMake) {
        this.getAllCarsByMake = getAllCarsByMake;
    }

    @Override
    public Either<Error, CarListResponse> process(CarColourRequest input) {
        return Try.of(() -> {
            return getAllCarsByMake.getAllCarsByMake(input.getColour());
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            return new CarNotFoundError();
        });
    }
}
