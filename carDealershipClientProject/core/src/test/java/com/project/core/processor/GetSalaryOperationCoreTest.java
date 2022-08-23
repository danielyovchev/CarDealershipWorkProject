package com.project.core.processor;

import com.project.api.model.employee.EmployeeSalaryRequest;
import com.project.data.db.entity.Employee;
import com.project.data.db.entity.Sales;
import com.project.data.db.repository.EmployeeRepository;
import com.project.data.db.repository.SalesRepository;
import com.project.data.domain.interfaces.GetSalaryService;
import com.salary.api.model.SalaryRequest;
import com.salary.api.model.SalaryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GetSalaryOperationCoreTest {
    @Mock
    private GetSalaryService getSalaryService;
    @Mock
    private SalesRepository salesRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private GetSalaryOperationCore getSalaryOperationCore;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process() {
        final Employee employee = new Employee();
        employee.setId(1L);
        employee.setSalary(2000.0);
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");

        final EmployeeSalaryRequest employeeSalaryRequest = new EmployeeSalaryRequest();
        employeeSalaryRequest.setId(1L);
        employeeSalaryRequest.setMonth(5);
        employeeSalaryRequest.setYear(2020);

        final YearMonth yearMonth = YearMonth.of(employeeSalaryRequest.getYear(), employeeSalaryRequest.getMonth());
        final LocalDate dateStart = yearMonth.atDay(1);
        final LocalDate dateEnd = yearMonth.atEndOfMonth();

        final Sales sale1 = new Sales();
        sale1.setDealType("cash");
        sale1.setPrice(5000.0);
        sale1.setDate(LocalDate.of(2020,5,10));
        sale1.setEmployeeId(1L);
        sale1.setCustomerId(2L);
        final Sales sale2 = new Sales();
        sale2.setDealType("cash");
        sale2.setPrice(2500.0);
        sale2.setDate(LocalDate.of(2020,5,12));
        sale2.setEmployeeId(1L);
        sale2.setCustomerId(2L);
        final Sales sale3 = new Sales();
        sale3.setDealType("cash");
        sale3.setPrice(3200.50);
        sale3.setDate(LocalDate.of(2020,5,16));
        sale3.setEmployeeId(1L);
        sale3.setCustomerId(2L);

        Mockito.when(employeeRepository.findById(employeeSalaryRequest.getId())).thenReturn(Optional.of(employee));
        Mockito.when(salesRepository.findAllByEmployeeIdAndDateBetween(employeeSalaryRequest.getId(),dateStart, dateEnd))
                .thenReturn(List.of(sale1, sale2,sale3));
        SalaryRequest salaryRequest = SalaryRequest.builder()
                .baseSalary(employee.getSalary())
                .soldCarsPrices(List.of(5000.0, 2500.0, 3200.50))
                .build();
        SalaryResponse salaryResponse = SalaryResponse.builder()
                .salary(2214.01)
                .bonus(214.01)
                .build();
        Mockito.when(getSalaryService.getSalary(any())).thenReturn(salaryResponse);

        assertEquals(2214.01, getSalaryOperationCore.process(employeeSalaryRequest).get().getSalary());
    }
}