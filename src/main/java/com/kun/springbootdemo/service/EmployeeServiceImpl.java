package com.kun.springbootdemo.service;

import com.kun.springbootdemo.error.EmployeeNotFoundException;
import com.kun.springbootdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    List<Employee> employees = new ArrayList<>();
    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            String id = UUID.randomUUID().toString();
            employee.setEmployeeId(id);
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
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found by Id"+ id));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee  employee = employees.stream().filter(e -> e.getEmployeeId().equals(id)).findFirst().get();
        employees.remove(employee);
        return "Employee is deleted with the id: " + id;
    }

    @Override
    public String deleteAllEmployees() {
        employees = new ArrayList<>();
        return "All employees are deleted";
    }
}
