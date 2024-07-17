package com.app.SptingProjection.controllers;

import com.app.SptingProjection.models.Department;
import com.app.SptingProjection.models.Employee;
import com.app.SptingProjection.repositories.EmployeeRepository;
import com.app.SptingProjection.services.EmployeesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeesController.class)
class EmployeesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeesService service;
    @MockBean
    private EmployeeRepository repository;

    @Test
    void getUsers() throws Exception {
        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department());
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Mockito.when(service.findAll()).thenReturn(employeeList);
        var a= mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(employee.getId()))
                .andExpect(jsonPath("[0].firstName").value(employee.getFirstName()))
                .andExpect(jsonPath("[0].lastName").value(employee.getLastName()))
                .andExpect(jsonPath("[0].position").value(employee.getPosition()))
                .andExpect( jsonPath("[0].salary").value(employee.getSalary()))
//                .andExpect( jsonPath("[0].department").value(employee.getDepartment()))
                .andReturn();

    }

    @Test
    void getEmployeeById() throws Exception {

        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department());
        Mockito.when(service.findOne(1)).thenReturn(employee);
        var a= mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
          //      .andExpect(jsonPath(".id").value(employee.getId()))
                .andExpect(jsonPath(".firstName").value(employee.getFirstName()))
                .andExpect(jsonPath(".lastName").value(employee.getLastName()))
                .andExpect(jsonPath(".position").value(employee.getPosition()))
                .andExpect( jsonPath(".salary").value(employee.getSalary()))
   //             .andExpect( jsonPath(".department").value(employee.getDepartment()))
                .andReturn();
    }

    @Test
    void full()throws Exception  {
        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department());
        String s=employee.getLastName()+"-"+employee.getFirstName();
        String p="position"+"-"+employee.getPosition();
        var a= mockMvc.perform(get("/employees/zsfzsf/full"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void create() throws Exception{
        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department());
        Mockito.when(repository.save(Mockito.any())).thenReturn(employee);
        mockMvc.perform(post("/employees")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void delete() throws Exception {
        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department());
        Mockito.when(repository.save(Mockito.any())).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isLoopDetected());
    }

    @Test
    void getDepartment() throws Exception {
        Employee employee = new Employee(1,"zsfzsf","zsfzsfzsf","zsfzsf",19800,new Department(19,List.of(new Employee()),"two"));
        Mockito.when(repository.save(Mockito.any())).thenReturn(employee);

       var response= mockMvc.perform(MockMvcRequestBuilders.get("/employees/1/department"))
               .andExpect(status().isOk())
                .andReturn();
    }
}