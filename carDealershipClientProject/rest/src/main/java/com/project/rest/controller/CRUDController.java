package com.project.rest.controller;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.GetAllCars;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import com.project.data.crud.interfaces.GetAllCarsByMake;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUDController {
    private final GetAllCars getAllCars;
    private final GetAllCarsByFuel getAllCarsByFuel;
    private final GetAllCarsByMake getAllCarsByMake;
    public CRUDController(GetAllCars getAllCars, GetAllCarsByFuel getAllCarsByFuel, GetAllCarsByMake getAllCarsByMake) {
        this.getAllCars = getAllCars;
        this.getAllCarsByFuel = getAllCarsByFuel;
        this.getAllCarsByMake = getAllCarsByMake;
    }
    @GetMapping("/getAllCars")
    public CarListResponse getAllCars(){
        return getAllCars.getAllCars();
    }
    @GetMapping("/getAllCarsByMake")
    public CarListResponse getByMake(@RequestParam String make){
        return getAllCarsByMake.getAllCarsByMake(make);
    }
    @GetMapping("/allByFuel")
    public CarListResponse getByFuel(@RequestParam String fuel){
        return getAllCarsByFuel.getAllCarsByFuel(fuel);
    }
}
