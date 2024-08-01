package com.rgarage.employeeportal.employee.service;

import com.rgarage.employeeportal.employee.domain.EmployeeAddressEntity;
import com.rgarage.employeeportal.employee.domain.EmployeeContactEntity;
import com.rgarage.employeeportal.employee.domain.model.CreateEmployeeRequest;
import com.rgarage.employeeportal.employee.domain.EmployeeEntity;
import com.rgarage.employeeportal.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    /*@Query("select e from EmployeeEntity e left join fetch e.contacts")*/
    public List<EmployeeEntity> find() {
        List<EmployeeEntity> employeeEntities =  this.employeeRepository.findAll();

        return employeeEntities;
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
