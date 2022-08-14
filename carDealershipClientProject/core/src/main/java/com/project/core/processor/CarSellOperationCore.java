package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotAvailableError;
import com.project.api.error.CarNotFoundError;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarDomainModel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class CarSellOperationCore implements CarSellOperation {
    private final MapCarFromApiService carFromApiService;

    public CarSellOperationCore(MapCarFromApiService carFromApiService) {
        this.carFromApiService = carFromApiService;
    }

    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        final CarDomainModel car = carFromApiService.getCar(carSellRequest.getVin());
        return Try.of(() -> {
            return CarSellResponse.builder().car(car.getMake()+" "+ car.getModel()).price(car.getPrice()).build();
        }).toEither().mapLeft( Throwable -> {
            if(Throwable.getMessage().isEmpty()){
                return new CarNotFoundError();
            }
            return new CarNotAvailableError();
        });
    }
}
