package com.EmployeeSystem.DTO;

import java.sql.Date;
import java.util.List;

public class AllDepartmentsDTO {
    private Long id;
    private String name;
    private Date creationDate;


    public AllDepartmentsDTO(String name, Long id, Date creationDate) {
        this.id = id;                             //if we don't give this it returns null values
        this.name = name;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
