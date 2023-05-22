package com.kun.springbootdemo.service;

import com.kun.springbootdemo.entity.EmployeeEntity;
import com.kun.springbootdemo.error.EmployeeNotFoundException;
import com.kun.springbootdemo.model.Employee;
import com.kun.springbootdemo.repository.EmployeeRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            String id = UUID.randomUUID().toString();
            employee.setEmployeeId(id);
        }
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeEntity -> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntity, employee);
            return employee;
        }).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(String id) {
       /* return employeeRepository.findAll().stream()
                .filter(employeeEntity -> employeeEntity.getEmployeeId().equals(id))
                .findFirst().map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found by Id"+ id));*/

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeEntity);
        return "Employee is deleted with the id: " + id;
    }

    @Override
    public String deleteAllEmployees() {
        employeeRepository.deleteAll();
        return "All employees are deleted";
    }
}
