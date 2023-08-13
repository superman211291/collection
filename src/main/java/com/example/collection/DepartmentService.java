package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getDepartmentEmployee(int id){
        List<Employee> employees = employeeService.getDepartmentEmployee(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Map<Integer, List<Employee>> getDepartmentsAll(){
        Map<Integer, List<Employee>> employees = employeeService.getDepartmentsAll();
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Employee getDepartmentEmployeeMinSalary(int id){
        Employee employees = employeeService.getDepartmentEmployeeMinSalary(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Employee getDepartmentEmployeeMaxSalary(int id){
        Employee employees = employeeService.getDepartmentEmployeeMaxSalary(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Double getDepartmentOurSalary(int id){
        return employeeService.getDepartmentOurSalary(id);
    }

}
