package com.spring.cart.demo.api.repository;

import com.spring.cart.demo.api.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    @Query("select e from EmployeeEntity e where e.id = :id") // ✅ 엔티티/필드/alias 사용
    Optional<EmployeeEntity> findByEmployee(@Param("id") Integer id);

    Page<EmployeeEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String q1, String q2, Pageable pageable);

}
