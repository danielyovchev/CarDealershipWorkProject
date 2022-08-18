package com.project.rest.controller;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUDController {
    private final GetAllCars getAllCars;
    private final GetAllCarsByFuel getAllCarsByFuel;
    private final GetAllCarsByMake getAllCarsByMake;
    private final GetAllCarsByColour getAllCarsByColour;
    private final GetAllCarsBetweenMileage getAllCarsBetweenMileage;
    public CRUDController(GetAllCars getAllCars, GetAllCarsByFuel getAllCarsByFuel, GetAllCarsByMake getAllCarsByMake, GetAllCarsByColour getAllCarsByColour, GetAllCarsBetweenMileage getAllCarsBetweenMileage) {
        this.getAllCars = getAllCars;
        this.getAllCarsByFuel = getAllCarsByFuel;
        this.getAllCarsByMake = getAllCarsByMake;
        this.getAllCarsByColour = getAllCarsByColour;
        this.getAllCarsBetweenMileage = getAllCarsBetweenMileage;
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
    @GetMapping("/allByColour")
    public CarListResponse getByColour(@RequestParam String colour){
        return getAllCarsByColour.getByColour(colour);
    }
    @GetMapping("/allBetweenMileage")
    public CarListResponse getBetweenMileage(@RequestParam Integer start, Integer end){
        return getAllCarsBetweenMileage.getByMileageBetween(start, end);
    }
}
