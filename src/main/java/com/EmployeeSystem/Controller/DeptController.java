package com.EmployeeSystem.Controller;

import com.EmployeeSystem.DTO.AllDepartmentsDTO;
import com.EmployeeSystem.DTO.ExpandDepartmentsDTO;
import com.EmployeeSystem.Exception.DepartmentHasEmployeesException;
import com.EmployeeSystem.Exception.DepartmentNotFoundException;
import com.EmployeeSystem.Modal.Department;
import com.EmployeeSystem.Service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeptController {

    @Autowired
    private DeptService dService;

    @PostMapping("/dept")
    public String createDepartment(@RequestBody Department department){
        dService.save(department);
        return "Department Created";
    }

    @PutMapping("/dept/update/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestBody Department department) throws DepartmentNotFoundException {
        dService.update(id, department);
        return "Department Updated";
    }

    @DeleteMapping("/dept/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) throws DepartmentHasEmployeesException, DepartmentNotFoundException {
        try {
            dService.delete(id);
            return ResponseEntity.ok("Department Deleted");
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DepartmentHasEmployeesException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // Consider using a more appropriate status code
        }
    }

    @GetMapping("/allDepartments")
    public ResponseEntity<List<AllDepartmentsDTO>> AllDepartments(){
        List<AllDepartmentsDTO> departmentsDTOS = dService.getAllDepartments();
        return ResponseEntity.ok(departmentsDTOS);
    }

    @GetMapping("/allDeptAndEmp")
    public ResponseEntity<List<ExpandDepartmentsDTO>> getDepartmentsAndEmployees(@RequestParam(value = "expand", required = false,defaultValue = "false") String expand){
        boolean expandEmployees = expand.equalsIgnoreCase("employee");
        List<ExpandDepartmentsDTO> departmentsDTOS = dService.getDepartments(expandEmployees);
        return ResponseEntity.ok(departmentsDTOS);
    }
}
