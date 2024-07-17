package com.app.SptingProjection.controllers;


import com.app.SptingProjection.models.Employee;
import com.app.SptingProjection.services.EmployeesService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping()
    public ResponseEntity getCustomer() {
        return new ResponseEntity<>(employeesService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") int id) {
        return new ResponseEntity<>(employeesService.findOne(id), HttpStatus.OK);
    }
    @GetMapping("/{firstName}/full")
    public ResponseEntity full(@PathVariable("firstName") String firstName){
        return new ResponseEntity<>(employeesService.employee(firstName),HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Employee employee) {
        employeesService.save(employee);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid Employee employee,
                                 @PathVariable("id") int id) {
       employeesService.update(id,employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        employeesService.delete(id);
        return new ResponseEntity<>(HttpStatus.LOOP_DETECTED);
    }

    @GetMapping("/{id}/department")
    public ResponseEntity getDepartment(@PathVariable("id") int id) {
        return new ResponseEntity<>(employeesService.getDepartment(id), HttpStatus.OK);
    }
}
