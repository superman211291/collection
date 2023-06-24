package com.example.collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {


    private final EmployeeService employeeService;


    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/departments/all")
    public String getDepartmentsAllEmployee() {
        return employeeService.getDepartmentsAll().toString();
    }

    @GetMapping("/departments/all")
    public String getDepartmentsAllEmployee(@RequestParam int departmentId) {
        return employeeService.printEmployee(employeeService.getDepartmentEmployee(departmentId));
    }

    @GetMapping("/departments/min-salary")
    public String getDepartmentsMinSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMinSalary(departmentId).toString();
    }

    @GetMapping("/departments/max-salary")
    public String getDepartmentsMaxSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMaxSalary(departmentId).toString();
    }
}
