// src/main/java/.../api/service/EmployeeService.java
package com.spring.cart.demo.api.service;

import com.spring.cart.demo.api.entity.EmployeeEntity;
import com.spring.cart.demo.api.dto.EmployeeRes;
import com.spring.cart.demo.api.repository.EmployeeRepository;
import com.spring.cart.demo.api.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public EmployeeRes getOne(Integer empNo) {
        EmployeeEntity e = repo.findById(empNo)
                .orElseThrow(() -> new NotFoundException("employee", empNo));
        return EmployeeRes.from(e);
    }
}
