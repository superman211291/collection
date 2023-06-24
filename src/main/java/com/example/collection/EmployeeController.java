package com.example.collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/hello")
    public String hello() {
        return "Привет!";
    }
    @GetMapping("/employee/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName,@RequestParam int department, @RequestParam double salary) {
        Employee employee = new Employee(firstName, lastName,department,salary);
        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeStorageIsFullException e) {
            return "ArrayIsFull";
        } catch (EmployeeAlreadyAddedException e) {
            return "EmployeeAlreadyAdded";
        }
        return employee.toString();
    }

    @GetMapping("/employee/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName,1,0.0);
        try {
            employeeService.deleteEmployee(firstName,lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/employee/find")
    public String findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee;
        try {
            employee = employeeService.searchEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/employee/getAll")
    public String getAllEmployee() {
        return employeeService.getAll();
    }

    @GetMapping("/employee/departments/all")
    public String getDepartmentsAllEmployee() {
        return employeeService.getDepartmentsAll();
    }

    @GetMapping("/employee/departments/allByID")
    public String getDepartmentsAllEmployee(@RequestParam int departmentId) {
        return employeeService.printEmployee(employeeService.getDepartmentEmployee(departmentId));
    }

    @GetMapping("/employee/departments/min-salary")
    public String getDepartmentsMinSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMinSalary(departmentId).toString();
    }

    @GetMapping("/employee/departments/max-salary")
    public String getDepartmentsMaxSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMaxSalary(departmentId).toString();
    }

}
