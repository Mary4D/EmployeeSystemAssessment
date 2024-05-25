package com.EmployeeSystem.DTO;

import com.EmployeeSystem.Modal.Department;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collector;

public class ALLEmployeesDTO {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private Double salary;
    private String address;
    private String role;
    private Date joiningDate;
    private Double yearlyBonusPercentage;

    public ALLEmployeesDTO(Long id, String name, Date dateOfBirth, Double salary, String address, String role, Date joiningDate, Double yearlyBonusPercentage) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.address = address;
        this.role = role;
        this.joiningDate = joiningDate;
        this.yearlyBonusPercentage = yearlyBonusPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getYearlyBonusPercentage() {
        return yearlyBonusPercentage;
    }

    public void setYearlyBonusPercentage(Double yearlyBonusPercentage) {
        this.yearlyBonusPercentage = yearlyBonusPercentage;
    }

}
