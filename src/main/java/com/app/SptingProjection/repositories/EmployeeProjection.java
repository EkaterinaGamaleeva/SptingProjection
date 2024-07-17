package com.app.SptingProjection.repositories;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    @Value("#{target.lastName + '-' + target.firstName}")
    String getFullName();
    @Value("#{'position' + '-' +target.position}")
    String getPosition();

    DepartmentProjection getDepartment();

interface DepartmentProjection{

    String getName();
}
//
//    //getFullName() (полное имя сотрудника), getPosition() (должность сотрудника), getDepartmentName()
}
