package com.rgarage.employeeportal.employee.domain;

import com.rgarage.employeeportal.employee.domain.model.Contact;
import com.rgarage.employeeportal.employee.domain.model.CreateEmployeeRequest;
import com.rgarage.employeeportal.employee.domain.model.Gender;
import com.rgarage.employeeportal.employee.domain.model.MaritalStatus;
import javax.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "marital_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Column(name = "hired_date", nullable = false)
    private LocalDateTime hiredDate;

    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<EmployeeContactEntity> contacts;

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

    public static EmployeeEntity from(CreateEmployeeRequest createEmployee) {

        return EmployeeEntity.builder()
                .firstName(createEmployee.employee().firstName())
                .lastName(createEmployee.employee().lastName())
                .gender(createEmployee.employee().gender())
                .maritalStatus(createEmployee.employee().maritalStatus())
                .birthDate((Instant.ofEpochMilli(createEmployee.employee().birthDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .hiredDate((Instant.ofEpochMilli(createEmployee.employee().hiredDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .position(createEmployee.employee().position())
                .build();
    }

    /*
    * mutation {
  createEmployee(input: {
    employee: {
      firstName: "Rickson",
      lastName: "Menezes",
      middleName: "Sushil",
      gender: MALE,
      position: "Lead",
      birthDate: 537552000000,
      hiredDate: 1686672000000,
      maritalStatus: MARRIED
    }
  }) {
    code
  }
}
}*/

}

