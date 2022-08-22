package com.project.data.crud.service;

import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.interfaces.GetAllCarsBetweenMileage;
import com.project.data.db.repository.CarRepository;
import com.project.data.domain.interfaces.MapCarFromApiService;
import com.project.data.domain.model.CarListDomainResponse;
import org.springframework.stereotype.Service;

@Service
public class GetAllCarsBetweenMileageServiceImpl implements GetAllCarsBetweenMileage {
    private final CarRepository carRepository;
    private final MapCarFromApiService carFromApiService;

    public GetAllCarsBetweenMileageServiceImpl(CarRepository carRepository, MapCarFromApiService carFromApiService) {
        this.carRepository = carRepository;
        this.carFromApiService = carFromApiService;
    }

    @Override
    public CarListDomainResponse getByMileageBetween(Integer start, Integer end) {
        if(carRepository.findAllByMileageBetween(start, end).isEmpty()){
            throw new CarNotFoundException();
        }
        return CarListDomainResponse.builder()
                .carDomainModelList(carRepository.findAllByMileageBetween(start, end)
                        .stream()
                        .map(x -> carFromApiService.getCar(x.getVin()))
                        .filter(x -> x.getStatus().equals("available"))
                        .toList()).build();
    }
}
