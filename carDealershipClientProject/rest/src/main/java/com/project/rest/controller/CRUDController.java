package com.project.rest.controller;

import com.project.api.model.carsByParam.CarListResponse;
import com.project.data.crud.interfaces.GetAllCarsByFuel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUDController {
    private final GetAllCarsByFuel getAllCarsByFuel;

    public CRUDController(GetAllCarsByFuel getAllCarsByFuel) {
        this.getAllCarsByFuel = getAllCarsByFuel;
    }
    @GetMapping("/allByFuel")
    public CarListResponse getByFuel(@RequestParam String fuel){
        return getAllCarsByFuel.getAllCarsByFuel(fuel);
    }
}
