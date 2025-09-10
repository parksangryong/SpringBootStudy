package com.spring.cart.demo.api.controller;

import com.spring.cart.demo.api.dto.EmployeeRes;
import com.spring.cart.demo.api.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeRes> getOne(@PathVariable Integer empId) {
        return ResponseEntity.ok(service.getOne(empId));
    }
}
