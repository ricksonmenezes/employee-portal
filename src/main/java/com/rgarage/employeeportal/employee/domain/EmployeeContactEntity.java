package com.rgarage.employeeportal.employee.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_contact")
@Getter
@Setter
public class EmployeeContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "emp_code")
    private EmployeeEntity employee;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
