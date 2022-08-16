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
        final Car car = carRepository.findByVin(carSellRequest.getVin()).orElseThrow(CarNotFoundException::new);
        final Customer customer = customerRepository.findById(Long.valueOf(carSellRequest.getCustomerId())).orElseThrow(CustomerNotFoundException::new);
        final Employee employee = employeeRepository.findById(Long.valueOf(carSellRequest.getEmployeeId())).orElseThrow(EmployeeNotFoundException::new);
        if(!carRepository.existsByVin(carSellRequest.getVin())){
            throw new CarNotFoundException();
        }
        Sales sale = new Sales();
        sale.setCarId(car.getId());
        sale.setCustomerId(customer.getId());
        sale.setEmployeeId(employee.getId());
        sale.setPrice(car.getPrice());
        sale.setDate(carSellRequest.getDate());
        salesRepository.save(sale);
        car.setStatus("sold");
        carRepository.save(car);
        customer.setBought(customer.getBought()+1);
        customerRepository.save(customer);
        return sale.getId();
    }
}
