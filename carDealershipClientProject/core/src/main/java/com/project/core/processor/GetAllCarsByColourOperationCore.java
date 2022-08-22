package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.InternalError;
import com.project.api.error.NoCarsForCriteriaError;
import com.project.api.model.carsByParam.CarColourRequest;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.operation.GetAllCarsByColourOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByColour;
import com.project.data.domain.mapper.DomainToResponseMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByColourOperationCore implements GetAllCarsByColourOperation {
    //returns all available cars by selected colour
    private final GetAllCarsByColour getAllCarsByColour;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByColourOperationCore(GetAllCarsByColour getAllCarsByColour, DomainToResponseMapper mapper) {
        this.getAllCarsByColour = getAllCarsByColour;
        this.mapper = mapper;
    }

    @Override
    public Either<Error, CarListResponse> process(final CarColourRequest input) {
        return Try.of(() -> {
            return CarListResponse.builder().carList(getAllCarsByColour.getByColour(input.getColour()).getCarDomainModelList()
                    .stream()
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
