package com.project.api.operation;

import com.project.api.base.OperationProcessor;
import com.project.api.model.carById.CarByIdRequest;
import com.project.api.model.carById.CarByIdResponse;

public interface GetCarByIdOperation extends OperationProcessor<CarByIdRequest, CarByIdResponse> {
}
