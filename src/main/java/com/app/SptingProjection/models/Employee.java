package com.app.SptingProjection.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "employees")
@ToString
@SuperBuilder
@NamedEntityGraphs(value = { @NamedEntityGraph(name = "employees",
                        attributeNodes = { @NamedAttributeNode(
                                        value = "department",
                                        subgraph = "name-subgraph")},
        subgraphs = { @NamedSubgraph( name = "name-subgraph", attributeNodes = { @NamedAttributeNode("name") })})})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "firstName")
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String firstName;

    @NotEmpty
    @Column(name = "lastName")
    @Size(min = 2, max = 1000, message = "Имя должно быть от 2 до 100 символов длиной")
    private String lastName;

    @NotEmpty(message = "Position should not be empty")
    @Column(name = "position")
    private String position;


    @NotNull
    @Column(name = "salary")
    @Min(value = 16990, message = "Age should be greater than 1")
    private int salary;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Employee() {

    }

    public Employee(int id, String firstName, String lastName, String position, int salary, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
