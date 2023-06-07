package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    private final Integer maxEmployee = 100;


    public void addEmployee(Employee employee) {
        if (employees.size() < maxEmployee) {
            if (employeeIsAdded(employee)) {
                throw new EmployeeAlreadyAddedException();
            } else {
                employees.add(employee);
            }
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }

    public void deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        if (employeeIsAdded(employee)) {
            employees.remove(employee);
        } else {
            throw new EmployeeNotFoundException();
        }
    }


    public Employee searchEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (    employee.getName().equals(employees.get(i).getName()) &&
                    employee.getSurname().equals(employees.get(i).getSurname())) {
                employee = employees.get(i);
                return employee;
            }
        }
        throw new EmployeeNotFoundException();


    }

    public boolean employeeIsAdded(Employee employee) {
        for (Employee value : employees) {
            if (value.equals(employee)) {
                return true;
            }
        }
        return false;
    }


}
