package com.project.rest.controller;

import com.project.api.base.Error;
import com.project.api.model.carSellModel.CarSellRequest;
import com.project.api.model.carSellModel.CarSellResponse;
import com.project.api.model.employee.EmployeeSalaryRequest;
import com.project.api.model.employee.EmployeeSalaryResponse;
import com.project.api.operation.CarSellOperation;
import com.project.api.operation.GetSalaryOperation;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final CarSellOperation carSellOperation;
    private final GetSalaryOperation getSalaryOperation;
    public HomeController(CarSellOperation carSellOperation, GetSalaryOperation getSalaryOperation) {
        this.carSellOperation = carSellOperation;
        this.getSalaryOperation = getSalaryOperation;
    }
    @PostMapping("/sellCar")
    public ResponseEntity<?> sellCar(@RequestBody CarSellRequest carSellRequest){
        Either<Error, CarSellResponse> result = carSellOperation.process(carSellRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
    @PostMapping("/salary")
    public ResponseEntity<?> showSalary(@RequestBody EmployeeSalaryRequest employeeSalaryRequest){
        Either<Error, EmployeeSalaryResponse> result = getSalaryOperation.process(employeeSalaryRequest);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
