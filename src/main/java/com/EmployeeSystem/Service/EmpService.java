package com.EmployeeSystem.Service;
import com.EmployeeSystem.DTO.ALLEmployeesDTO;
import com.EmployeeSystem.Exception.DepartmentNotFoundException;
import com.EmployeeSystem.Exception.EmployeeNotFoundException;
import com.EmployeeSystem.Modal.Department;
import com.EmployeeSystem.Modal.Employee;
import com.EmployeeSystem.DTO.EmployeeDTO;
import com.EmployeeSystem.Repository.DeptRepository;
import com.EmployeeSystem.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepo;
    @Autowired
    private DeptRepository deptRepo;

    public void save(Employee employee){
        empRepo.save(employee);
    }

    public Employee update(Long id, Employee employee) throws EmployeeNotFoundException{
        Optional<Employee> result = empRepo.findById(id);

        Employee existingEmployee;
        if(result.isPresent()){
            existingEmployee = result.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setDateOfBirth(employee.getDateOfBirth());
            existingEmployee.setJoiningDate(employee.getJoiningDate());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());
            existingEmployee.setReportingManager(employee.getReportingManager());
            empRepo.save(existingEmployee);
            return existingEmployee;
        }
        throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
    }

    public void updateEmpDept(Long id, Long d_id) throws EmployeeNotFoundException, DepartmentNotFoundException{
        Employee employee = empRepo.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with ID" + id + " not found"));
        Department newDepartment = deptRepo.findById(d_id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + id + " not found"));
        employee.setDepartment(newDepartment);
        empRepo.save(employee);
    }

    public Page<EmployeeDTO> getEmployeeLookupData(Pageable pageable){
        Page<Employee> employees = empRepo.findAll(pageable);
        Page<EmployeeDTO> employeeDTOs = employees.map(employee -> new EmployeeDTO(employee.getId(),employee.getName()));
        return employeeDTOs;
    }

    public Page<ALLEmployeesDTO> getAllEmployees(Pageable pageable){
        Page<Employee> employees = empRepo.findAll(pageable);
        Page<ALLEmployeesDTO> employeeDTOs = employees.map(employee -> new ALLEmployeesDTO(employee.getId(), employee.getName(), employee.getDateOfBirth(),employee.getSalary(),employee.getAddress(),employee.getRole(),employee.getJoiningDate(),employee.getYearlyBonusPercentage()));
        return employeeDTOs;
    }

    public List<ALLEmployeesDTO> getAllEmployeesByDepartment(Long d_id){
        List<Employee> employees = empRepo.findByDepartmentId(d_id);
        System.out.println("Number of Employees retrieved: " + employees.size());
        List<ALLEmployeesDTO> employeeDTOs = new ArrayList<>();
        for(Employee employee:employees){
            employeeDTOs.add(new ALLEmployeesDTO(employee.getId(), employee.getName(),employee.getDateOfBirth(),employee.getSalary(),employee.getAddress(),employee.getRole(),employee.getJoiningDate(),employee.getYearlyBonusPercentage()));
        }
        return employeeDTOs;
    }

}
