package com.project.data.crud.service;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.mapper.DomainToResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByMakeServiceImpl implements GetAllCarsByMake {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;
    private final DomainToResponseMapper mapper;

    public GetAllCarsByMakeServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService, DomainToResponseMapper mapper) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
        this.mapper = mapper;
    }

    @Override
    public CarListResponse getAllCarsByMake(String make) {
        if(carRepository.findAllByMake(make).isEmpty()){
            throw new CarNotFoundException();
        }
        return CarListResponse.builder()
                .carList(carRepository.findAllByMake(make)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .map(mapper::mapCar)
                        .toList()).build();
    }
}
