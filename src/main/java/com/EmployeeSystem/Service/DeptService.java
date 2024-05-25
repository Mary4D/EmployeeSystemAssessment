package com.EmployeeSystem.Service;

import com.EmployeeSystem.DTO.AllDepartmentsDTO;
import com.EmployeeSystem.DTO.ExpandDepartmentsDTO;
import com.EmployeeSystem.Exception.DepartmentHasEmployeesException;
import com.EmployeeSystem.Exception.DepartmentNotFoundException;
import com.EmployeeSystem.Modal.Department;
import com.EmployeeSystem.Repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepo;

    public void save(Department department) {
        deptRepo.save(department);
    }

    public Department update(Long id,Department department) throws DepartmentNotFoundException {
        Optional<Department> result = deptRepo.findById(id);

        Department existingDepartment;
        if (result.isPresent()) {
            existingDepartment = result.get();
            existingDepartment.setName(department.getName());
            existingDepartment.setCreationDate(department.getCreationDate());
            existingDepartment.setDepartmentHead(department.getDepartmentHead());
            deptRepo.save(existingDepartment);
            return existingDepartment;
        }
        throw new DepartmentNotFoundException("Department with ID " + id + " not found");
    }

    public void delete(Long id) throws DepartmentNotFoundException, DepartmentHasEmployeesException {
        Optional<Department> result = deptRepo.findById(id);

        if(result.isEmpty()){
            throw new DepartmentNotFoundException("Department with ID " + id + " not found");
        }
        Department department = result.get();
        if(!department.getEmployees().isEmpty()){
            throw new DepartmentHasEmployeesException("Department with ID " + id + " has assigned employees. Cannot delete.");
        }
        deptRepo.deleteById(id);
    }

    public List<AllDepartmentsDTO> getAllDepartments(){
        List<Department> departments = deptRepo.findAll();
        List<AllDepartmentsDTO> departmentsDTOs = new ArrayList<>();
        for (Department department:departments){
            departmentsDTOs.add(new AllDepartmentsDTO(department.getName(),department.getId(),department.getCreationDate()));
        }
        return departmentsDTOs;
    }

    @Autowired
    private EmpService empService;

    public  List<ExpandDepartmentsDTO> getDepartments(boolean expandEmployees){
        List<Department> departments = deptRepo.findAll();
        List<ExpandDepartmentsDTO> departmentsDTOs = new ArrayList<>();
        for(Department department:departments){
            ExpandDepartmentsDTO departmentDTO= new ExpandDepartmentsDTO(department.getName(), department.getId(),department.getCreationDate());
            if(expandEmployees){
                departmentDTO.setEmployees(empService.getAllEmployeesByDepartment(department.getId()));
            }
            departmentsDTOs.add(departmentDTO);
        }
        return departmentsDTOs;
    }
}


