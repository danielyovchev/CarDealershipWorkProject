package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.InternalError;
import com.project.api.error.NoCarsForCriteriaError;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.MileageRequest;
import com.project.api.operation.GetAllCarsBetweenMileageOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsBetweenMileage;
import com.project.data.domain.mapper.DomainToResponseMapper;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsBetweenMileageOperationCore implements GetAllCarsBetweenMileageOperation {
    // returns all available cars between given mileage
    private final GetAllCarsBetweenMileage getAllCarsBetweenMileage;
    private final DomainToResponseMapper mapper;

    public GetAllCarsBetweenMileageOperationCore(GetAllCarsBetweenMileage getAllCarsBetweenMileage, DomainToResponseMapper mapper) {
        this.getAllCarsBetweenMileage = getAllCarsBetweenMileage;
        this.mapper = mapper;
    }

    @Override
    public Either<Error, CarListResponse> process(final MileageRequest input) {
        return Try.of(() -> {
            return CarListResponse.builder().carList(getAllCarsBetweenMileage.getByMileageBetween(input.getStart(), input.getEnd())
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
