package com.project.data.crud.service;

import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarListDomainResponse;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsByMakeServiceImpl implements GetAllCarsByMake {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;

    public GetAllCarsByMakeServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
    }

    @Override
    public CarListDomainResponse getAllCarsByMake(String make) {
        if(carRepository.findAllByMake(make).isEmpty()){
            throw new CarNotFoundException();
        }
        return CarListDomainResponse.builder()
                .carDomainModelList(carRepository.findAllByMake(make)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .toList()).build();
    }
}
