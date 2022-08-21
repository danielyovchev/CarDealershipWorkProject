package com.project.core.processor;

import com.project.api.base.Error;
import com.project.api.error.SalaryCalculationError;
import com.project.api.model.employee.EmployeeSalaryRequest;
import com.project.api.model.employee.EmployeeSalaryResponse;
import com.project.api.operation.GetSalaryOperation;
import com.project.data.crud.exception.EmployeeNotFoundException;
import com.project.data.db.entity.Employee;
import com.project.data.db.entity.Sales;
import com.project.data.db.repository.EmployeeRepository;
import com.project.data.db.repository.SalesRepository;
import com.project.data.domain.interfaces.GetSalaryService;
import com.salary.api.model.SalaryRequest;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class GetSalaryOperationCore implements GetSalaryOperation {
    // operation for calculation employee's salary depending on how many car he sold during given month
    private final GetSalaryService getSalaryService;
    private final SalesRepository salesRepository;
    private final EmployeeRepository employeeRepository;

    public GetSalaryOperationCore(GetSalaryService getSalaryService, SalesRepository salesRepository, EmployeeRepository employeeRepository) {
        this.getSalaryService = getSalaryService;
        this.salesRepository = salesRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Either<Error, EmployeeSalaryResponse> process(final EmployeeSalaryRequest input) {

        return Try.of(() -> {
            final Employee employee = employeeRepository.findById(input.getId()).orElseThrow(EmployeeNotFoundException::new);
            final YearMonth yearMonth = YearMonth.of(input.getYear(), input.getMonth());
            final LocalDate dateStart = yearMonth.atDay(1);
            final LocalDate dateEnd = yearMonth.atEndOfMonth();
            final SalaryRequest salaryRequest = SalaryRequest.builder()
                    .baseSalary(employee.getSalary())
                    .soldCarsPrices(salesRepository.findAllByEmployeeIdAndDateBetween(input.getId(), dateStart, dateEnd).stream().map(Sales::getPrice).toList())
                    .build();
            return EmployeeSalaryResponse.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .salary(getSalaryService.getSalary(salaryRequest).getSalary())
                    .bonus(getSalaryService.getSalary(salaryRequest).getBonus())
                    .month(yearMonth.getMonth().toString())
                    .year(String.valueOf(yearMonth.getYear()))
                    .build();
                })
                .toEither()
                .mapLeft(Throwable -> {
                    if(Throwable instanceof EmployeeNotFoundException){
                        throw new EmployeeNotFoundException();
                    }
                    return new SalaryCalculationError();
                });
    }
}
