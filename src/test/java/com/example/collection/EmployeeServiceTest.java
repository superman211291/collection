package com.example.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private QuestionService employeeService;
    @BeforeEach
    public void setUp(){
        employeeService = new QuestionService();
    }

    @Test
    void addEmployee() {
        Question employee = new Question("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        List<Question> resultEmployees = employeeService.getAll();
        Assertions.assertEquals(employee,resultEmployees.get(0));
    }
    @Test
    void shouldThrowEmployeeStorageIsFullException() {
        for (int i = 0; i < 10; i++) {
            employeeService.addEmployee(new Question("Илья"+i,"Строев"+i,1,150000));
        }
        Question employee = new Question("Илья","Строев",1,150000);
        assertThrows(EmployeeStorageIsFullException.class, ()-> employeeService.addEmployee(employee));
    }
    @Test
    void shouldThrowEmployeeAlreadyAddedException() {
        Question employee = new Question("Илья","Строев",1,150000);
        Question employee1 = new Question("Илья","Строев",2,120000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeAlreadyAddedException.class, ()-> employeeService.addEmployee(employee1));
    }

    @Test
    void deleteEmployee() {
        Question employee = new Question("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        Question resultEmployee = employeeService.searchEmployee("Илья","Строев");
        Assertions.assertEquals(employee,resultEmployee);
        employeeService.deleteEmployee("Илья","Строев");
        List<Question> resultList = employeeService.getAll();
        Assertions.assertEquals(0,resultList.size());
    }

    @Test
    void shouldThrowEmployeeNotFoundException() {
        Question employee = new Question("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.deleteEmployee("Федор","Петров"));
    }

    @Test
    void searchEmployee() {
        Question employee = new Question("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        Question resultEmployee = employeeService.searchEmployee("Илья","Строев");
        Assertions.assertEquals(employee,resultEmployee);
    }

    @Test
    void shouldThrowSearchEmployeeNotFoundException() {
        Question employee = new Question("Илья","Строев",1,150000);
        employeeService.addEmployee(employee);
        assertThrows(EmployeeNotFoundException.class, ()-> employeeService.searchEmployee("Федор","Петров"));
    }
}