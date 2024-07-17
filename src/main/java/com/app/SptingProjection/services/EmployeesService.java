package com.app.SptingProjection.services;
import com.app.SptingProjection.models.Department;
import com.app.SptingProjection.models.Employee;
import com.app.SptingProjection.repositories.EmployeeProjection;
import com.app.SptingProjection.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeesService {
    private EmployeeRepository employeeRepository;

@Autowired
    public EmployeesService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(int id) {
        return employeeRepository.findById(id).orElse(null);

    }
    public List< EmployeeProjection> employee(String firstName) {
     return employeeRepository.findByFirstName(firstName);
    }


    @Transactional
    public void save(Employee employee) {
    employeeRepository.save(employee);
        ;
    }

    @Transactional
    public void update(int id, Employee updateEmployee) {
    Employee employee=employeeRepository.findById(id).get();
        updateEmployee.setId(id);
        updateEmployee.setDepartment(employee.getDepartment());
        employeeRepository.save(updateEmployee);
    }

    @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public String getDepartment(int id) {
       return employeeRepository.findById(id).get().getDepartment().getName();
    }
}