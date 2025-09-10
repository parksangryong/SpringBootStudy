package com.spring.cart.demo.api.dto;

import com.spring.cart.demo.api.entity.EmployeeEntity;
import java.time.LocalDate;

public record EmployeeRes(Integer id, String firstName, String lastName, LocalDate hireDate) {
    public static EmployeeRes from(EmployeeEntity e) {
        return new EmployeeRes(e.getId(), e.getFirstName(), e.getLastName(), e.getHireDate());
    }
}