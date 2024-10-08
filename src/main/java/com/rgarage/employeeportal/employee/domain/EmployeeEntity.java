package com.rgarage.employeeportal.employee.domain;

import com.rgarage.employeeportal.employee.domain.model.*;

import javax.persistence.*;
import lombok.*;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "employee")
   @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    private Set<EmployeeContactEntity> contacts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @Fetch(org.hibernate.annotations.FetchMode.JOIN)
    private Set<EmployeeAddressEntity> addresses;

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

        EmployeeEntity employee =  EmployeeEntity.builder()
                .firstName(createEmployee.employee().firstName())
                .middleName(createEmployee.employee().middleName())
                .lastName(createEmployee.employee().lastName())
                .gender(createEmployee.employee().gender())
                .maritalStatus(createEmployee.employee().maritalStatus())
                .birthDate((Instant.ofEpochMilli(createEmployee.employee().birthDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .hiredDate((Instant.ofEpochMilli(createEmployee.employee().hiredDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .position(createEmployee.employee().position())
                .build();

        employee.setContacts(convertToEmployeeContactEntities(createEmployee.contacts(), employee));
        employee.setAddresses(convertToEmployeeAddressEntities(createEmployee.addresses(), employee));

        return  employee;
    }

    public static EmployeeEntity from(UpdateEmployeeRequest updateEmployee) {

        EmployeeEntity employee =  EmployeeEntity.builder()
                .code(updateEmployee.employee().code())
                .firstName(updateEmployee.employee().firstName())
                .middleName(updateEmployee.employee().middleName())
                .lastName(updateEmployee.employee().lastName())
                .gender(updateEmployee.employee().gender())
                .maritalStatus(updateEmployee.employee().maritalStatus())
                .birthDate((Instant.ofEpochMilli(updateEmployee.employee().birthDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .hiredDate((Instant.ofEpochMilli(updateEmployee.employee().hiredDate())).atZone(ZoneOffset.UTC).toLocalDateTime())
                .position(updateEmployee.employee().position())
                .build();

        employee.setContacts(convertToEmployeeContactEntitiesForUpdate(updateEmployee.contacts(), employee));
        employee.setAddresses(convertToEmployeeAddressEntitiesForUpdate(updateEmployee.addresses(), employee));

        return  employee;
    }

    private static Set<EmployeeContactEntity> convertToEmployeeContactEntities(final Set<Contact> contacts, final EmployeeEntity employee) {

        return (contacts).stream()
                .map(contact -> {
                    EmployeeContactEntity employeeContact = new EmployeeContactEntity(contact.contact(), contact.isPrimary());
                    employeeContact.setEmployee(employee);
                    return employeeContact;

                })
                .collect(Collectors.toSet());
    }

    private static Set<EmployeeContactEntity> convertToEmployeeContactEntitiesForUpdate(final Set<UpdateContact> contacts, final EmployeeEntity employee) {

        return (contacts).stream()
                .map(contact -> {
                    EmployeeContactEntity employeeContact = new EmployeeContactEntity(contact.id(), contact.contact(), contact.isPrimary());
                    employeeContact.setEmployee(employee);
                    return employeeContact;

                })
                .collect(Collectors.toSet());
    }

    private static Set<EmployeeAddressEntity> convertToEmployeeAddressEntities(final Set<Address> addresses, final EmployeeEntity employee) {

        return (addresses).stream()
                .map(address -> {
                    EmployeeAddressEntity employeeAddress = new EmployeeAddressEntity(address.address1(), address.address2(),address.isPrimary());
                    employeeAddress.setEmployee(employee);
                    return employeeAddress;

                })
                .collect(Collectors.toSet());
    }

    private static Set<EmployeeAddressEntity> convertToEmployeeAddressEntitiesForUpdate(final Set<UpdateAddress> addresses, final EmployeeEntity employee) {

        return (addresses).stream()
                .map(address -> {
                    EmployeeAddressEntity employeeAddress = new EmployeeAddressEntity(address.id(), address.address1(), address.address2(),address.isPrimary());
                    employeeAddress.setEmployee(employee);
                    return employeeAddress;

                })
                .collect(Collectors.toSet());
    }



}

