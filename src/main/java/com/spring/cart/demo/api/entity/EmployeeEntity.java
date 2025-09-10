package com.spring.cart.demo.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    // 샘플 DB는 키가 이미 있으므로 자동생성 제거
    @Column(name = "emp_no")
    private Integer id;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "first_name", nullable = false, length = 14)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Enumerated(EnumType.STRING)                 // "M"/"F"
    @Column(name = "gender", nullable = false, length = 1)
    private GenderEnum gender;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    // --- 기본 생성자 & getter/setter (Jackson 직렬화 위해 필요) ---
    protected EmployeeEntity() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public GenderEnum getGender() { return gender; }
    public void setGender(GenderEnum gender) { this.gender = gender; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
}
