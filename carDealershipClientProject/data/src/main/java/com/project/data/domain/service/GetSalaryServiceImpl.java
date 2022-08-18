package com.project.data.domain.service;

import com.project.data.domain.interfaces.GetSalaryService;
import com.salary.api.feign.SalaryFeign;
import com.salary.api.model.SalaryRequest;
import com.salary.api.model.SalaryResponse;
import org.springframework.stereotype.Service;

@Service
public class GetSalaryServiceImpl implements GetSalaryService {
    private final SalaryFeign salaryFeign;

    public GetSalaryServiceImpl(SalaryFeign salaryFeign) {
        this.salaryFeign = salaryFeign;
    }

    @Override
    public SalaryResponse getSalary(SalaryRequest salaryRequest) {
        return salaryFeign.getSalary(salaryRequest);
    }
}
