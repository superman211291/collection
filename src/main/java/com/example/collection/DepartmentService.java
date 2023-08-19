package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    private final QuestionService employeeService;

    public DepartmentService(QuestionService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Question> getDepartmentEmployee(int id){
        List<Question> employees = employeeService.getDepartmentEmployee(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Map<Integer, List<Question>> getDepartmentsAll(){
        Map<Integer, List<Question>> employees = employeeService.getDepartmentsAll();
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Question getDepartmentEmployeeMinSalary(int id){
        Question employees = employeeService.getDepartmentEmployeeMinSalary(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Question getDepartmentEmployeeMaxSalary(int id){
        Question employees = employeeService.getDepartmentEmployeeMaxSalary(id);
        if (employees == null){
            throw  new NotFoundDepartmentException();
        }
        return employees;
    }

    public Double getDepartmentOurSalary(int id){
        return employeeService.getDepartmentOurSalary(id);
    }

}
