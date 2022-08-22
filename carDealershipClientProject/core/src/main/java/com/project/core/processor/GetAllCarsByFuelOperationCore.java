package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.InternalError;
import com.project.api.error.NoCarsForCriteriaError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.FuelRequest;
import com.project.api.operation.GetAllCarsByFuelOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.domain.mapper.DomainToResponseMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByFuelOperationCore implements GetAllCarsByFuelOperation {
    // returns all available cars by selected fuel
    private final GetAllCarsByFuel getAllCarsByFuel;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByFuelOperationCore(GetAllCarsByFuel getAllCarsByFuel, DomainToResponseMapper mapper) {
        this.getAllCarsByFuel = getAllCarsByFuel;
        this.mapper = mapper;
    }

    @Override
    public Either<Error, CarListResponse> process(final FuelRequest input) {
        return Try.of(() -> {
            return CarListResponse.builder().carList(getAllCarsByFuel.getAllCarsByFuel(input.getFuel())
                    .getCarDomainModelList().stream()
                    .map(mapper::mapCar)
                    .toList()).build();
        }).toEither().mapLeft(Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new NoCarsForCriteriaError();
            }
            return new InternalError();
        });
    }
}
