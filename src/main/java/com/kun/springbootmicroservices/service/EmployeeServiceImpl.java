package com.kun.springbootmicroservices.service;

import com.kun.springbootmicroservices.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    List<Employee> employees = new ArrayList<>();
    @Override
    public Employee save(Employee employee) {
        if(employee.getId() == null || employee.getEmailId().isEmpty()){
            String id = UUID.randomUUID().toString();
            employee.setId(id);
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employees.stream().
                filter(employee -> employee.getId().equals(id)).
                findFirst().get();
       /* Employee target = null;
        for (Employee employee: employees) {
            if (employee.getId().equals(id))
                target = employee;
        }
        return target;*/
    }
}
