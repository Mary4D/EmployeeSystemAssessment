package com.EmployeeSystem.Controller;

import com.EmployeeSystem.DTO.ALLEmployeesDTO;
import com.EmployeeSystem.Exception.DepartmentNotFoundException;
import com.EmployeeSystem.Exception.EmployeeNotFoundException;
import com.EmployeeSystem.Modal.Employee;
import com.EmployeeSystem.DTO.EmployeeDTO;
import com.EmployeeSystem.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmpService eService;

    @PostMapping("/employee")
    public String createEmployee(@RequestBody Employee employee){
        eService.save(employee);
        return "Employee Created";
    }

    @PutMapping("/emp/update/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws EmployeeNotFoundException{
        eService.update(id, employee);
        return "Employee Updated";
    }

    @PutMapping("/emp/move_dept/{id}")
    public String updateEmployeeDepartment(@PathVariable Long id, @RequestParam Long d_id) throws EmployeeNotFoundException, DepartmentNotFoundException{
        eService.updateEmpDept(id, d_id);
        return "Employee moved to another department";
    }

    @GetMapping("/employees")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(@RequestParam(value = "lookup", required = false, defaultValue = "false") boolean lookup){
        Page<EmployeeDTO> employeeDTOs = eService.getEmployeeLookupData(Pageable.ofSize(20));
        return ResponseEntity.ok(employeeDTOs);
      //  return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<Page<ALLEmployeesDTO>> AllEmployees(){
        Page<ALLEmployeesDTO> employeeDTOs = eService.getAllEmployees(Pageable.ofSize(20));
        return ResponseEntity.ok(employeeDTOs);
    }
}




















