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
            if(employeeIsAdded(employee)){
                throw new EmployeeAlreadyAddedException();
            }else {
                employees.add(employee);
            }
        } else {
            throw new EmployeeStorageIsFullException();
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
        if (employee == null){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public boolean employeeIsAdded(Employee employee){
        for (Employee value : employees) {
            if (value.equals(employee)) {
                return true;
            }
        }
        return false;
    }




}
