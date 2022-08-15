package com.project.data.crud.service;

import com.project.api.model.carSellModel.CarSellRequest;
import com.project.data.crud.exception.CarNotFoundException;
import com.project.data.crud.exception.CustomerNotFoundException;
import com.project.data.crud.exception.EmployeeNotFoundException;
import com.project.data.crud.interfaces.CreateSaleService;
import com.project.data.db.entity.Car;
import com.project.data.db.entity.Customer;
import com.project.data.db.entity.Employee;
import com.project.data.db.entity.Sales;
import com.project.data.db.repository.CarRepository;
import com.project.data.db.repository.CustomerRepository;
import com.project.data.db.repository.EmployeeRepository;
import com.project.data.db.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateSaleServiceImpl implements CreateSaleService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SalesRepository salesRepository;

    public CreateSaleServiceImpl(CarRepository carRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, SalesRepository salesRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.salesRepository = salesRepository;
    }

    @Override
    public Long createSale(CarSellRequest carSellRequest) {
        final Optional<Car> car = carRepository.findByVin(carSellRequest.getVin());
        final Optional<Customer> customer = customerRepository.findById(Long.valueOf(carSellRequest.getCustomerId()));
        final Optional<Employee> employee = employeeRepository.findById(Long.valueOf(carSellRequest.getEmployeeId()));
        if(!carRepository.existsByVin(carSellRequest.getVin())){
            throw new CarNotFoundException();
        }
        if(!customerRepository.existsById(Long.valueOf(carSellRequest.getCustomerId()))){
            throw new CustomerNotFoundException();
        }
        if(!employeeRepository.existsById(Long.valueOf(carSellRequest.getEmployeeId()))){
            throw new EmployeeNotFoundException();
        }
        Sales sale = new Sales();
        sale.setCarId(car.get().getId());
        sale.setCustomerId(customer.get().getId());
        sale.setEmployeeId(employee.get().getId());
        sale.setPrice(car.orElseThrow().getPrice());
        sale.setDate(carSellRequest.getDate());
        salesRepository.save(sale);
        return sale.getId();
    }
}
