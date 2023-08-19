package com.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private QuestionService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Question> employees;
    @BeforeEach
    public void setUp(){
        employees = new ArrayList<>();
        employees.add(new Question("Илья","Строев",1,150000));
        employees.add(new Question("Федор", "Петров", 1, 200000));
        employees.add(new Question("Максим", "Каширин", 2, 120000));
    }
    @Test
    void getDepartmentEmployee() {
        assertNotNull(employeeService);
        List<Question> employees1 = new ArrayList<>();
        employees1.add(employees.get(0));
        employees1.add(employees.get(1));
        Mockito.when(employeeService.getDepartmentEmployee(1)).thenReturn(employees1);
        List<Question> expected = departmentService.getDepartmentEmployee(1);
        Mockito.verify(employeeService).getDepartmentEmployee(1);
        assertEquals(expected,employees1);
    }
    @Test
    void shouldThrowNotFoundDepartmentException() {
        assertNotNull(employeeService);
        Mockito.when(employeeService.getDepartmentEmployee(1)).thenReturn(null);
        assertThrows(NotFoundDepartmentException.class, ()-> departmentService.getDepartmentEmployee(1));
        Mockito.verify(employeeService).getDepartmentEmployee(1);

        Mockito.when(employeeService.getDepartmentsAll()).thenReturn(null);
        assertThrows(NotFoundDepartmentException.class, ()-> departmentService.getDepartmentsAll());
        Mockito.verify(employeeService).getDepartmentsAll();

        Mockito.when(employeeService.getDepartmentEmployeeMinSalary(1)).thenReturn(null);
        assertThrows(NotFoundDepartmentException.class, ()-> departmentService.getDepartmentEmployeeMinSalary(1));
        Mockito.verify(employeeService).getDepartmentEmployeeMinSalary(1);

        Mockito.when(employeeService.getDepartmentEmployeeMaxSalary(1)).thenReturn(null);
        assertThrows(NotFoundDepartmentException.class, ()-> departmentService.getDepartmentEmployeeMaxSalary(1));
        Mockito.verify(employeeService).getDepartmentEmployeeMaxSalary(1);
    }

    @Test
    void getDepartmentsAll() {
        assertNotNull(employeeService);
        Map<Integer, List<Question>> result = new HashMap<>();
        List<Question> employees1 = new ArrayList<>();
        employees1.add(employees.get(0));
        employees1.add(employees.get(1));
        List<Question> employees2 = new ArrayList<>();
        employees1.add(employees.get(2));
        result.put(1,employees1);
        result.put(2,employees2);
        Mockito.when(employeeService.getDepartmentsAll()).thenReturn(result);
        Map<Integer, List<Question>> expected = departmentService.getDepartmentsAll();
        Mockito.verify(employeeService).getDepartmentsAll();
        assertEquals(expected,result);
    }

    @Test
    void getDepartmentEmployeeMinSalary() {
        assertNotNull(employeeService);
        Question employee1 = employees.get(0);
        Mockito.when(employeeService.getDepartmentEmployeeMinSalary(1)).thenReturn(employee1);
        Question expected = departmentService.getDepartmentEmployeeMinSalary(1);
        Mockito.verify(employeeService).getDepartmentEmployeeMinSalary(1);
        assertEquals(expected,employee1);
    }

    @Test
    void getDepartmentEmployeeMaxSalary() {
        assertNotNull(employeeService);
        Question employee1 = employees.get(1);
        Mockito.when(employeeService.getDepartmentEmployeeMaxSalary(1)).thenReturn(employee1);
        Question expected = departmentService.getDepartmentEmployeeMaxSalary(1);
        Mockito.verify(employeeService).getDepartmentEmployeeMaxSalary(1);
        assertEquals(expected,employee1);
    }

    @Test
    void getDepartmentOurSalary() {
        assertNotNull(employeeService);
        Double ourSalary = employees.get(0).getSalary() + employees.get(1).getSalary();
        Mockito.when(employeeService.getDepartmentOurSalary(1)).thenReturn(ourSalary);
        Double expected = departmentService.getDepartmentOurSalary(1);
        Mockito.verify(employeeService).getDepartmentOurSalary(1);
        assertEquals(expected,ourSalary);
    }
}