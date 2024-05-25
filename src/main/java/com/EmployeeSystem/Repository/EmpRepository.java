package com.EmployeeSystem.Repository;

import com.EmployeeSystem.Modal.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpRepository extends JpaRepository<Employee,Long>{
    List<Employee> findByDepartmentId(Long id);

    Page<Employee> findAll(Pageable pageable);
}
