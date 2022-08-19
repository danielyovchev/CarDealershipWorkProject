package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.AllCarRequest;
import com.project.api.model.carsByParam.CarListResponse;
import io.vavr.control.Either;

public interface GetAllCarsOperation extends OperationProcessor<AllCarRequest, CarListResponse> {
    @Override
    Either<Error, CarListResponse> process(AllCarRequest input);
}
