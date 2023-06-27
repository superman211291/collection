package com.example.collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    private final EmployeeService employeeService;


    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/allId")
    public String getDepartmentsAllEmployee(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployee(departmentId).toString();
    }

    @GetMapping("/all")
    public String getDepartmentsAllEmployee() {
        StringBuilder stringBuilder = new StringBuilder();
        Integer department = employeeService.getDepartmentsAll().stream().findFirst().get().getDepartment();;
        for (Employee employee : employeeService.getDepartmentsAll()) {
            if(employee.getDepartment() != department){
                stringBuilder.append("____________________");
                stringBuilder.append("\n");
            }
            department=employee.getDepartment();
            stringBuilder.append(employee);
            stringBuilder.append("\n");
        }


        return stringBuilder.toString();
    }


    @GetMapping("/min-salary")
    public String getDepartmentsMinSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMinSalary(departmentId).toString();
    }

    @GetMapping("/max-salary")
    public String getDepartmentsMaxSalary(@RequestParam int departmentId) {
        return employeeService.getDepartmentEmployeeMaxSalary(departmentId).toString();
    }
}
