package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carsByParam.AllCarRequest;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.operation.GetAllCarsOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCars;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GetAllCarsOperationCore implements GetAllCarsOperation {
    private final GetAllCars getAllCars;

    public GetAllCarsOperationCore(GetAllCars getAllCars) {
        this.getAllCars = getAllCars;
    }

    @Override
    public Either<Error, CarListResponse> process(AllCarRequest input) {
        return Try.of(() -> {
            return (CarListResponse) getAllCars.getAllCars()
                    .getCarList().stream()
                    .map(x -> {
                        return x.getStatus().equals("available");
                    })
                    .collect(Collectors.toList());
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            return new CarNotFoundError();
        });
    }
}
