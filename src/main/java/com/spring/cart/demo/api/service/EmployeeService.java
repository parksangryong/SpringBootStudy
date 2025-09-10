// src/main/java/.../api/service/EmployeeService.java
package com.spring.cart.demo.api.service;

import com.spring.cart.demo.api.entity.EmployeeEntity;
import com.spring.cart.demo.api.dto.EmployeeRes;
import com.spring.cart.demo.api.common.AppException;
import com.spring.cart.demo.api.error.Errors;
import com.spring.cart.demo.api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public EmployeeRes getOne(Integer empNo) {
        EmployeeEntity e = repo.findById(empNo)
                .orElseThrow(() -> new AppException(Errors.USER.USER_NOT_FOUND));
        return EmployeeRes.from(e);
    }
}
