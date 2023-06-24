package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    private final Integer maxEmployee = 10;


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
        Employee employee = new Employee(firstName,lastName,1,0.0);
        if (employeeIsAdded(employee)) {
            employees.remove(employee);
        } else {
            throw new EmployeeNotFoundException();
        }
    }


    public Employee searchEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName,1, 0.0d);
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

        return employees
                .stream()
                .anyMatch(employee1 -> employee1.equals(employee));
    }


    public String getAll() {
        StringBuilder stringBuilder = new StringBuilder();
        employees.forEach(employee -> stringBuilder.append(employee.toString()));
        return stringBuilder.toString();
    }

    public String getDepartmentsAll() {
        StringBuilder stringBuilder = new StringBuilder();
        employees
                .stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .forEach(employee -> stringBuilder.append(employee.toString()));
        return stringBuilder.toString();
    }

    public void indexingSalary(int percent) {
        employees.forEach(employee -> employee.setSalary(employee.getSalary() + (employee.getSalary() / 100 * percent)));
    }

    public double getOurSalary() {
        return employees
                .stream()
                .map(Employee::getSalary)
                .mapToDouble(f-> f)
                .sum();
    }

    public Employee getEmployeeMinSalary(List<Employee> employees) {

        return employees
                .stream()
                .filter(employee -> employee.getSalary() == employees
                        .stream()
                        .map(Employee::getSalary)
                        .min(Comparator.naturalOrder())
                        .get())
                .findFirst()
                .get();
    }

    public Employee getEmployeeMaxSalary(List<Employee> employees) {
        return employees
                .stream()
                .filter(employee -> employee.getSalary() == employees
                        .stream()
                        .map(Employee::getSalary)
                        .max(Comparator.naturalOrder())
                        .get())
                .findFirst()
                .get();
    }

    public double getAverageSalary() {
        return getOurSalary() / employees.size();
    }

    public String getFullNameAll() {
        StringBuilder str = null;
        employees.stream().forEach(employee->str.append(employee.getSurname() + " " + employee.getName()));
        return str.toString();
    }

    public Employee getDepartmentEmployeeMinSalary(int department) {
        return getEmployeeMinSalary(employees
                    .stream()
                    .filter(employee -> employee.getDepartment() == department)
                    .collect(Collectors.toList()));
    }

    public Employee getDepartmentEmployeeMaxSalary(int department) {
        return getEmployeeMaxSalary(employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList()));
    }

    public double getDepartmentOurSalary(int department) {
        return employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .mapToDouble(Double::floatValue)
                .sum();
    }

    public double getDepartmentAverageSalary(int department) {
        return getDepartmentOurSalary(department) / getCountDepartmentEmployee(department);
    }

    public void indexingDepartmentSalary(int department, int percent) {
        employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(employee.getSalary() + (employee.getSalary() / 100 * percent)));

    }

    public List<Employee> getDepartmentEmployee(int department) {
        return employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public void printEmployeeWithoutDepartment(Employee employee) {
        System.out.println(employee.getId() + " " +
                employee.getName() + " " +
                employee.getSurname() + " " +
                employee.getSalary()
        );
    }

    public List<Employee> getAllEmployeeLessSalary(float salary) {

        return employees
                .stream()
                .filter(employee -> employee.getSalary()<salary)
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployeeMoreSalary(float salary) {
        return employees
                .stream()
                .filter(employee -> employee.getSalary()>salary)
                .collect(Collectors.toList());
    }

    private Employee getEmployee(String name, String surname) {
        Employee employee = new Employee(name,surname,1,0.0d);
      return employees
               .stream()
               .filter(employee1 -> employee1.equals(employee))
               .findFirst()
              .orElseThrow(()->new IllegalArgumentException("Такого сотрудника не существует"));

    }


    public static double getGeneratedSalary() {
        double leftLimit = 1;
        double rightLimit = 500_000d;
        return leftLimit + (new Random().nextFloat() * (rightLimit - leftLimit));
    }

    public int getCountDepartmentEmployee(int department) {

        return (int)employees
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .count();
    }

    public void changeSalary(String name, String surname, String patronymic, float newSalary) {
        getEmployee(name, surname).setSalary(newSalary);
    }

    public void changeDepartment(String name, String surname, String patronymic, int department) {
        getEmployee(name, surname).setDepartment(department);
    }

    public String printEmployee(List<Employee> employees){
        StringBuilder stringBuilder = new StringBuilder();
        employees.forEach(employee -> stringBuilder.append(employee.toString()));
        return stringBuilder.toString();
    }


}
