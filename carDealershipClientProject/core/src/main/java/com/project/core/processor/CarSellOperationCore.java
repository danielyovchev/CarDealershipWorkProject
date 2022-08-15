package com.project.core.processor;

import com.price.api.feign.PriceFeign;
import com.price.api.model.PriceRequest;
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
    private final PriceFeign priceFeign;
    public CarSellOperationCore(MapCarFromApiService carFromApiService, CreateSaleService createSaleService, PriceFeign priceFeign) {
        this.carFromApiService = carFromApiService;
        this.createSaleService = createSaleService;
        this.priceFeign = priceFeign;
    }

    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        final CarDomainModel car = carFromApiService.getCar(carSellRequest.getVin());
        final PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBasePrice(car.getPrice());
        priceRequest.setCustomerPurchasedCars(1);
        priceRequest.setMonths(carSellRequest.getMonths());
        priceRequest.setType(carSellRequest.getDealType());
        return Try.of(() -> {
            createSaleService.createSale(carSellRequest);
            return CarSellResponse.builder()
                    .car(car.getMake()+" "+ car.getModel())
                    .price(priceFeign.getPrice(priceRequest).getPrice())
                    .message("Tomorrow more")
                    .build();
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
