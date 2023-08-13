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
        return employeeService.getDepartmentEmployee(id);
    }

    public Map<Integer, List<Employee>> getDepartmentsAll(){
        return employeeService.getDepartmentsAll();
    }

    public Employee getDepartmentEmployeeMinSalary(int id){
        return employeeService.getDepartmentEmployeeMinSalary(id);
    }

    public Employee getDepartmentEmployeeMaxSalary(int id){
        return employeeService.getDepartmentEmployeeMaxSalary(id);
    }

    public Double getDepartmentOurSalary(int id){
        return employeeService.getDepartmentOurSalary(id);
    }

}
