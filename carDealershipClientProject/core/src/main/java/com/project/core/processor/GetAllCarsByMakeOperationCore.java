package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.CarMakeRequest;
import com.project.api.operation.GetAllCarsByMakeOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByMakeOperationCore implements GetAllCarsByMakeOperation {
    private final GetAllCarsByMake getAllCarsByMake;

    public GetAllCarsByMakeOperationCore(GetAllCarsByMake getAllCarsByMake) {
        this.getAllCarsByMake = getAllCarsByMake;
    }

    @Override
    public Either<Error, CarListResponse> process(CarMakeRequest input) {
        return Try.of(() -> {
            return getAllCarsByMake.getAllCarsByMake(input.getMake());
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            return new CarNotFoundError();
        });
    }
}