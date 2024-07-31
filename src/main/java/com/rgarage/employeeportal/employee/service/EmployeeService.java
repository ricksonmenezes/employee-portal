package com.rgarage.employeeportal.employee.service;

import com.rgarage.employeeportal.employee.domain.model.CreateEmployeeRequest;
import com.rgarage.employeeportal.employee.domain.EmployeeEntity;
import com.rgarage.employeeportal.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> find() {
        return this.employeeRepository.findAll();
    }

    public EmployeeEntity findOneById(Integer code) {
        return this.employeeRepository.findById(code).orElse(null);
    }

    public EmployeeEntity create(CreateEmployeeRequest createEmployee) {
        return this.employeeRepository.save(EmployeeEntity.from(createEmployee));
    }

    public boolean delete(Integer code) {
        if (this.employeeRepository.existsById(code)) {
            this.employeeRepository.deleteById(code);
            return true;
        }
        return false;
    }

}
