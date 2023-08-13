package com.example.collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        checkData(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), department, salary);
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
        checkData(firstName,lastName);
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), 1, 0.0);
        try {
            employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        checkData(firstName,lastName);
        Employee employee;
        try {
            employee = employeeService.searchEmployee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/getAll")
    public String getAllEmployee() {
        return employeeService.getAll().toString();
    }

    private void checkData(String firstName, String lastName){
        char[] russianLetters = new char[64];
        for (int i = 0; i < 64; i++) {
            russianLetters[i]= (char)(1040+i);
        }
        if(     !StringUtils.containsOnly(firstName, russianLetters) ||
                !StringUtils.containsOnly(lastName, russianLetters) ){
             throw new DataException();
        }


    }


}
