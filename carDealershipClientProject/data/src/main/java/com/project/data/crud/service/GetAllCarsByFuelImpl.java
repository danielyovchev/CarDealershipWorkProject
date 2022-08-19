package com.project.data.crud.service;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.mapper.DomainToResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByFuelImpl implements GetAllCarsByFuel {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByFuelImpl(CarRepository carRepository, MapCarFromApiService carFromApiService, DomainToResponseMapper mapper) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
        this.mapper = mapper;
    }

    @Override
    public CarListResponse getAllCarsByFuel(String fuel) {

        return CarListResponse.builder()
                .carList(carRepository.findAllByFuel(fuel)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .map(mapper::mapCar)
                .toList()).build();
    }
}
