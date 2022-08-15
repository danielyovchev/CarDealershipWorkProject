package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.CarNotAvailableError;
import com.project.api.error.CarNotFoundError;
import com.project.api.error.CustomerNotFoundError;
import com.project.api.error.EmployeeNotFoundError;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.exception.CustomerNotFoundException;
import com.project.data.crud.exception.EmployeeNotFoundException;
import com.project.data.crud.interfaces.CreateSaleService;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarDomainModel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class CarSellOperationCore implements CarSellOperation {
    private final MapCarFromApiService carFromApiService;
    private final CreateSaleService createSaleService;
    public CarSellOperationCore(MapCarFromApiService carFromApiService, CreateSaleService createSaleService) {
        this.carFromApiService = carFromApiService;
        this.createSaleService = createSaleService;
    }

    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        final CarDomainModel car = carFromApiService.getCar(carSellRequest.getVin());
        return Try.of(() -> {
            createSaleService.createSale(carSellRequest);
            return CarSellResponse.builder().car(car.getMake()+" "+ car.getModel()).price(car.getPrice()).build();
        }).toEither().mapLeft( Throwable -> {
            if(Throwable instanceof CarNotFoundException){
                return new CarNotFoundError();
            }
            if(Throwable instanceof CustomerNotFoundException){
                return new CustomerNotFoundError();
            }
            if(Throwable instanceof EmployeeNotFoundException){
                return new EmployeeNotFoundError();
            }
            return new CarNotAvailableError();
        });
    }
}
