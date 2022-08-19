package com.project.data.crud.service;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.GetAllCarsByColour;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.mapper.DomainToResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByColourServiceImpl implements GetAllCarsByColour {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByColourServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService, DomainToResponseMapper mapper) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
        this.mapper = mapper;
    }

    @Override
    public CarListResponse getByColour(String colour) {
        return CarListResponse.builder()
                .carList(carRepository.findAllByColour(colour)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .map(mapper::mapCar)
                        .toList()).build();
    }
}
