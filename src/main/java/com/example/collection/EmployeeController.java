package com.example.collection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Привет!";
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeStorageIsFullException e) {
            return "ArrayIsFull";
        } catch (EmployeeAlreadyAddedException e) {
            return "EmployeeAlreadyAdded";
        }
        return employee.toString();
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName, 1, 0.0);
        try {
            employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee;
        try {
            employee = employeeService.searchEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/getAll")
    public String getAllEmployee() {
        return employeeService.getAll().toString();
    }


}
