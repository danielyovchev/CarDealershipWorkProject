package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.error.InternalError;
import com.project.api.error.NoCarsForCriteriaError;
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
    //list of all cars available
    private final GetAllCars getAllCars;

    public GetAllCarsOperationCore(GetAllCars getAllCars) {
        this.getAllCars = getAllCars;
    }

    @Override
    public Either<Error, CarListResponse> process(AllCarRequest input) {
        return Try.of(getAllCars::getAllCars).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new NoCarsForCriteriaError();
            }
            return new InternalError();
        });
    }
}
