package com.project.core.processor;

import com.price.api.model.PriceRequest;
import com.price.api.model.PriceResponse;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.data.crud.interfaces.CreateSaleService;
import com.project.data.db.entity.Customer;
import com.project.data.db.repository.CustomerRepository;
import com.project.data.domain.interfaces.GetPriceService;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarDomainModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class CarSellOperationCoreTest {
    @Mock
    private MapCarFromApiService carFromApiService;
    @Mock
    private CreateSaleService createSaleService;
    @Mock
    private GetPriceService getPriceService;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CarSellOperationCore carSellOperationCore;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process() {
        final CarSellRequest carSellRequest = CarSellRequest.builder()
                .vin("15646565461dasd")
                .employeeId(1)
                .customerId(1)
                .dealType("cash")
                .months(40)
                .date(LocalDate.of(2021,6,20)).build();
        final CarDomainModel car = CarDomainModel.builder()
                .make("Ford")
                .model("Focus")
                .price(6000.0)
                .build();
        final Customer customer = new Customer();
        customer.setFirstName("Ivan");
        customer.setLastName("Ivanov");
        customer.setBought(5);
        customer.setId(1L);
        final PriceRequest priceRequest = PriceRequest.builder()
                .basePrice(car.getPrice())
                .customerPurchasedCars(5)
                .months(carSellRequest.getMonths())
                .type(carSellRequest.getDealType())
                .build();
        final PriceResponse priceResponse = PriceResponse.builder()
                .price(7050.20)
                .message("etc")
                .build();
        Mockito.when(carFromApiService.getCar(carSellRequest.getVin())).thenReturn(car);
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(getPriceService.getPriceFromService(priceRequest)).thenReturn(priceResponse);
        Mockito.when(createSaleService.createSale(any(), any())).thenReturn(null);

        assertEquals(7050.20, carSellOperationCore.process(carSellRequest).get().getPrice());
    }
}