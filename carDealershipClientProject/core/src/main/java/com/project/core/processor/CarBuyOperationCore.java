package com.project.core.processor;

import com.example.api.feign.ApiFeignClient;
import com.example.api.model.CarApiResponseModel;
import com.project.api.base.Error;
import com.project.api.error.CarAlreadyExistsError;
import com.project.api.error.CarNotFoundError;
import com.project.api.error.InternalError;
import com.project.api.model.carBuyModel.CarBuyRequest;
import com.project.api.model.carBuyModel.CarBuyResponse;
import com.project.api.model.carBuyModel.CreateCarRequest;
import com.project.api.operation.CarBuyOperation;
import com.project.data.crud.exception.CarAlreadyExistsException;
import com.project.data.crud.interfaces.CreateCarService;
import com.project.data.domain.interfaces.MapCarFromApiService;
import feign.FeignException;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class CarBuyOperationCore implements CarBuyOperation {
    private final CreateCarService createCarService;
    private final ApiFeignClient apiFeignClient;
    public CarBuyOperationCore(CreateCarService createCarService, MapCarFromApiService carFromApiService, ApiFeignClient apiFeignClient) {
        this.createCarService = createCarService;
        this.apiFeignClient = apiFeignClient;
    }

    @Override
    public Either<Error, CarBuyResponse> process(CarBuyRequest input) {
        return Try.of(() -> {
            if(input.getMileage()>350000){
                return CarBuyResponse.builder().message("Car has done too many kilometers").build();
            }
            final CarApiResponseModel carApiResponseModel = apiFeignClient.getCar(input.getVin());
            CreateCarRequest createCarRequest =CreateCarRequest.builder()
                    .vin(input.getVin())
                    .make(carApiResponseModel.getMake())
                    .model(carApiResponseModel.getModel())
                    .fuel(carApiResponseModel.getFuel())
                    .mileage(input.getMileage())
                    .price(input.getPrice())
                    .year(carApiResponseModel.getYear())
                    .colour(input.getColour())
                    .build();
            createCarService.addCar(createCarRequest);
            return CarBuyResponse.builder()
                    .message("Car " + createCarRequest.getMake() + " " + createCarRequest.getModel() + " is bought for "
                            + createCarRequest.getPrice())
                    .build();
                })
                .toEither().mapLeft(Throwable -> {
                    if(Throwable instanceof CarAlreadyExistsException){
                        return new CarAlreadyExistsError();
                    }
                    if(Throwable instanceof FeignException){
                        return new CarNotFoundError();
                    }
                    return new InternalError();
                });
    }
}
