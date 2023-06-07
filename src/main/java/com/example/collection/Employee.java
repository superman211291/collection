package com.example.collection;

import java.util.Objects;

public class Employee {
    private final int id;
    private static int count;
    private String name;
    private String surname;

    public Employee(String name, String surname) {
            this.name = name;
            this.surname = surname;
            this.id = count;
            count++;
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
        return "{\"firstName\": " + "\"" + name +  "\", "+
                   "\"lastName\": " + "\"" + surname +  "\" }" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return  Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
