package com.app.SptingProjection.services;


import com.app.SptingProjection.models.Department;
import com.app.SptingProjection.models.Employee;
import com.app.SptingProjection.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DepartmentsService {
    private DepartmentRepository departmentRepository;
@Autowired
    public DepartmentsService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findOne(int id) {
        Optional<Department> foundBook = departmentRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Department department) {

    departmentRepository.save(department);
    }

    @Transactional
    public void update(int id, Department updatedDepartmen) {
    List<Employee> employees=departmentRepository.findById(id).get().getEmployees();
        updatedDepartmen.setId(id);
        updatedDepartmen.setEmployees(employees);
        departmentRepository.save(updatedDepartmen);
    }

    @Transactional
    public void delete(int id) {
        departmentRepository.deleteById(id);
    }



    public List<Employee> getEmployee(int id) {
       return departmentRepository.findById(id).get().getEmployees();
    }

}
