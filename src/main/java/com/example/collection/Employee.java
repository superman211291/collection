package com.example.collection;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

public class Employee  {
    private final int id;
    private static int count;
    private String name;
    private String surname;
    private int department;
    private double salary;

    public Employee(String name, String surname, int department, double salary) {
        this.id = count;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
        count++;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "{\"firstName\": " + "\"" + name + "\", " +
                "\"lastName\": " + "\"" + surname + "\" " +
                "\"department\": " + "\"" + department + "\", " +
                "\"salary\": " + "\"" + salary + "\" }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
