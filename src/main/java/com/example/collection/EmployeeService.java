package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    private final Integer maxEmployee = 100;


    public void addEmployee(Employee employee){
        if (employees.size()<maxEmployee){
            employees.add(employee);
        }
    }
    public void deleteEmployee(Integer index){
        employees.remove(index);
    }

    public Employee searchEmployee(String nameOrSurname){
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++) {
            employee = employees.get(i);
            if (employee.getName().equals(nameOrSurname) ||
                employee.getSurname().equals(nameOrSurname)){
               break;
            }
        }
        return employee;
    }


}
