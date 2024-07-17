package com.app.SptingProjection.repositories;

import com.app.SptingProjection.models.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
  @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, value = "employees")
   List<EmployeeProjection> findByFirstName(String firstName);
}
