package com.project.core.processor;

import com.price.api.model.PriceRequest;
import com.project.api.base.Error;
import com.project.api.error.*;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.operation.CarSellOperation;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.exception.CustomerNotFoundException;
import com.project.data.crud.exception.EmployeeNotFoundException;
import com.project.data.crud.interfaces.CreateSaleService;
import com.project.data.db.repository.CustomerRepository;
import com.project.data.domain.exception.InvalidPriceException;
import com.project.data.domain.interfaces.GetPriceService;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarDomainModel;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

@Service
public class CarSellOperationCore implements CarSellOperation {
    private final MapCarFromApiService carFromApiService;
    private final CreateSaleService createSaleService;
    private final GetPriceService getPriceService;
    private final CustomerRepository customerRepository;
    public CarSellOperationCore(MapCarFromApiService carFromApiService, CreateSaleService createSaleService, GetPriceService getPriceService, CustomerRepository customerRepository) {
        this.carFromApiService = carFromApiService;
        this.createSaleService = createSaleService;
        this.getPriceService = getPriceService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Either<Error, CarSellResponse> process(CarSellRequest carSellRequest) {
        final CarDomainModel car = carFromApiService.getCar(carSellRequest.getVin());
        final PriceRequest priceRequest = PriceRequest.builder()
                .basePrice(car.getPrice())
                .customerPurchasedCars(customerRepository.findById(Long.valueOf(carSellRequest.getCustomerId()))
                        .orElseThrow(CustomerNotFoundException::new).getBought())
                .months(carSellRequest.getMonths())
                .type(carSellRequest.getDealType())
                .build();
        return Try.of(() -> {
            createSaleService.createSale(carSellRequest, getPriceService.getPriceFromService(priceRequest).getPrice());
            return CarSellResponse.builder()
                    .car(car.getMake()+" "+ car.getModel())
                    .price(getPriceService.getPriceFromService(priceRequest).getPrice())
                    .message(getPriceService.getPriceFromService(priceRequest).getMessage())
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
            if(Throwable instanceof InvalidPriceException){
                return new InvalidPricingError();
            }
            return new CarNotAvailableError();
        });
    }
}
