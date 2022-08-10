package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotAvailableError;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carById.CarByIdRequest;
import com.project.api.model.carById.CarByIdResponse;
import com.project.api.operation.GetCarByIdOperation;
import com.project.data.db.entity.Car;
import com.project.data.db.repository.CarRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GetCarByIdOperationCore implements GetCarByIdOperation {
    private final CarRepository carRepository;

    public GetCarByIdOperationCore(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Either<Error, CarByIdResponse> process(CarByIdRequest input) {
        return Try.of(() -> {
            final Car car = carRepository.findById(Long.valueOf(input.getId())).orElseThrow();
            return CarByIdResponse.builder().make(car.getMake()).model(car.getModel()).build();
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof NoSuchElementException){
                return new CarNotFoundError();
            }
            return new CarNotAvailableError();
        });
    }
}
