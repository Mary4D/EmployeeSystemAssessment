package com.EmployeeSystem.Repository;

import com.EmployeeSystem.Modal.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Department,Long> {
}
