package com.app.SptingProjection.controllers;

import com.app.SptingProjection.models.Department;
import com.app.SptingProjection.models.Employee;
import com.app.SptingProjection.repositories.DepartmentRepository;
import com.app.SptingProjection.services.DepartmentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentsController.class)
class DepartmentsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DepartmentsService service;
    @MockBean
    private DepartmentRepository repository;



    @Test
    void getFindAll() throws Exception {
        Department department=new Department(1, List.of(new Employee()),"two");
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        Mockito.when(service.findAll()).thenReturn(departments);
        var a= mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(department.getId()))
                .andExpect(jsonPath("[0].name").value(department.getName()))
//                .andExpect(jsonPath("[0].employees").value(department.getEmployees().get(0).getLastName()))
                .andReturn();
    }

    @Test
    void findOne() throws Exception {
        Department department=new Department(1, List.of(new Employee()),"two");
        Mockito.when(service.findOne(1)).thenReturn(department);
        var a= mockMvc.perform(get("/departments/1"))
               .andExpect(status().isOk())
//                .andExpect(jsonPath(".id").value(department.getId()))
                .andExpect(jsonPath(".name").value(department.getName()))
//                .andExpect(jsonPath(".employees").value(department.getEmployees()))
                .andReturn();

    }


    @Test
    void create() throws Exception {
        Department department=new Department(1, List.of(new Employee()),"two");
        Mockito.when(repository.save(Mockito.any())).thenReturn(department);
        mockMvc.perform(post("/departments")
                        .content(objectMapper.writeValueAsString(department))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void delete() throws Exception {
        Department department=new Department(1, List.of(new Employee()),"two");
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isLoopDetected());
    }

}