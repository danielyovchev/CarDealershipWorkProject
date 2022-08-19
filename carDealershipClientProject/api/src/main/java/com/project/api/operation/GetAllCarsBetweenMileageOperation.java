package com.project.api.operation;

import com.project.api.base.Error;
import com.project.api.base.OperationProcessor;
import com.project.api.model.carsByParam.CarListResponse;
import com.project.api.model.carsByParam.MileageRequest;
import io.vavr.control.Either;

public interface GetAllCarsBetweenMileageOperation extends OperationProcessor<MileageRequest, CarListResponse> {
    @Override
    Either<Error, CarListResponse> process(MileageRequest input);
}
