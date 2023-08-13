package com.example.collection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public String getDepartmentsIdAllEmployee(@PathVariable("id") String departmentId) {
        return departmentService.getDepartmentEmployee(Integer.parseInt(departmentId)).toString();
    }

    @GetMapping("/employees")
    public String getDepartmentsAllEmployee() {
        return departmentService.getDepartmentsAll().toString();
    }

    @GetMapping("/{id}/salary/min")
    public String getDepartmentsIdMinSalary(@PathVariable("id") String departmentId) {
        int id = Integer.parseInt(departmentId);
        Employee employee = departmentService.getDepartmentEmployeeMinSalary(id);
        return employee.toString();
    }

    @GetMapping("/{id}/salary/max")
    public String getDepartmentsIdMaxSalary(@PathVariable("id") String departmentId) {
        int id = Integer.parseInt(departmentId);
        Employee employee = departmentService.getDepartmentEmployeeMaxSalary(id);
        return employee.toString();
    }

    @GetMapping("/{id}/salary/sum")
    public String getDepartmentsIdOurSalary(@PathVariable("id") String departmentId) {
        return departmentService.getDepartmentOurSalary(Integer.parseInt(departmentId)).toString();
    }


}
