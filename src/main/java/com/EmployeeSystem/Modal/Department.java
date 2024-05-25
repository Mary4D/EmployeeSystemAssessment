package com.EmployeeSystem.Modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "deptDetails")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @OneToOne
    @JoinColumn(name = "department_head_id")
    private Employee departmentHead;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @JsonManagedReference
    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Employee departmentHead) {
        this.departmentHead = departmentHead;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department(Long id) {
        this.id = id;
    }

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", departmentHead=" + departmentHead +
                '}';
    }

}
