package com.app.SptingProjection.controllers;


import com.app.SptingProjection.models.Department;
import com.app.SptingProjection.services.DepartmentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private final DepartmentsService departmentsService;
@Autowired
    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }


    @GetMapping
    public ResponseEntity getFindAll() {
        return new ResponseEntity<>( departmentsService.findAll(),HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable("id") int id) {
        return new ResponseEntity(departmentsService.findOne(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Department department)  {
        departmentsService.save(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid Department department,
                                             @PathVariable("id") int id)  {
        departmentsService.update(id,department);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        departmentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.LOOP_DETECTED);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployees(@PathVariable("id") int id) {
        return new ResponseEntity<>(departmentsService.getEmployee(id),HttpStatus.OK);
    }

}