package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.MileageRequest;
import com.project.api.operation.GetAllCarsBetweenMileageOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsBetweenMileage;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class GerAllCarsBetweenMileageOperationCore implements GetAllCarsBetweenMileageOperation {
    private final GetAllCarsBetweenMileage getAllCarsBetweenMileage;

    public GerAllCarsBetweenMileageOperationCore(GetAllCarsBetweenMileage getAllCarsBetweenMileage) {
        this.getAllCarsBetweenMileage = getAllCarsBetweenMileage;
    }

    @Override
    public Either<Error, CarListResponse> process(MileageRequest input) {
        return Try.of(() -> {
            return getAllCarsBetweenMileage.getByMileageBetween(input.getStart(), input.getEnd());
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            return new CarNotFoundError();
        });
    }
}
