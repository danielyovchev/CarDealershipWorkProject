package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.InternalError;
import com.project.api.error.NoCarsForCriteriaError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.CarMakeRequest;
import com.project.api.operation.GetAllCarsByMakeOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import com.project.data.domain.mapper.DomainToResponseMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByMakeOperationCore implements GetAllCarsByMakeOperation {
    // returns all available cars by selected car make
    private final GetAllCarsByMake getAllCarsByMake;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByMakeOperationCore(GetAllCarsByMake getAllCarsByMake, DomainToResponseMapper mapper) {
        this.getAllCarsByMake = getAllCarsByMake;
        this.mapper = mapper;
    }

    @Override
    public Either<Error, CarListResponse> process(final CarMakeRequest input) {
        return Try.of(() -> {
            return CarListResponse.builder().carList(getAllCarsByMake.getAllCarsByMake(input.getMake())
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
