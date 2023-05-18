package com.kun.springbootmicroservices.service;

import com.kun.springbootmicroservices.model.Employee;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Override
    public Employee save(Employee employee) {
        if(employee.getId() == null || employee.getEmailId().isEmpty()){
            String id = UUID.randomUUID().toString();
            employee.setId(id);
        }
        return employee;
    }
}
