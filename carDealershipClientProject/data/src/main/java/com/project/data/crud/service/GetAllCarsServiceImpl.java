package com.project.data.crud.service;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.GetAllCars;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.mapper.DomainToResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsServiceImpl implements GetAllCars {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;
    private final DomainToResponseMapper mapper;

    public GetAllCarsServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService, DomainToResponseMapper mapper) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
        this.mapper = mapper;
    }

    @Override
    public CarListResponse getAllCars() {
        return CarListResponse.builder()
                .carList(carRepository.findAll().stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .map(mapper::mapCar)
                        .toList()).build();
    }
}
