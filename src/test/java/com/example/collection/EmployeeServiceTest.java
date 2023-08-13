package com.example.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    @BeforeEach
    public void setUp(){
        employeeService = new EmployeeService();
    }

    @Test
    void addEmployee() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        List<Employee> resultEmployees = employeeService.getAll();
        Assertions.assertEquals(employee,resultEmployees.get(0));
    }
    @Test
    void shouldThrowEmployeeStorageIsFullException() {
        for (int i = 0; i < 10; i++) {
            employeeService.addEmployee(new Employee("Илья"+i,"Строев"+i,1,150000));
        }
        Employee employee = new Employee("Илья","Строев",1,150000);
        assertThrows(EmployeeStorageIsFullException.class, ()-> employeeService.addEmployee(employee));
    }
    @Test
    void shouldThrowEmployeeAlreadyAddedException() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        Employee employee1 = new Employee("Илья","Строев",2,120000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService.addEmployee(employee1));
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        Employee resultEmployee = employeeService.searchEmployee("Илья","Строев");
        Assertions.assertEquals(employee,resultEmployee);
        employeeService.deleteEmployee("Илья","Строев");
        List<Employee> resultList = employeeService.getAll();
        Assertions.assertEquals(0,resultList.size());
    }

    @Test
    void shouldThrowEmployeeNotFoundException() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.deleteEmployee("Федор","Петров"));
    }

    @Test
    void searchEmployee() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        Employee resultEmployee = employeeService.searchEmployee("Илья","Строев");
        Assertions.assertEquals(employee,resultEmployee);
    }

    @Test
    void shouldThrowSearchEmployeeNotFoundException() {
        Employee employee = new Employee("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.searchEmployee("Федор","Петров"));
    }
}