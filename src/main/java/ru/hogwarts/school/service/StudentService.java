package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();

    public Student create(String name, int age) {
        checkIllegal(name, age);
        Student student = new Student(name, age);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student delete(String name, int age) {
        checkIllegal(name, age);
        Student student = new Student(name, age);
        checkNotFound(student);
        return studentMap.remove(student.getId());
    }

    public Student update(String oldName, int oldAge, String name, int age) {
        checkIllegal(oldName, oldAge);
        checkIllegal(name, age);
        Student student = new Student(oldName, oldAge);
        Student newStudent = new Student(name, age);
        newStudent.setId(student.getId());
        checkNotFound(student);
        return studentMap.put(newStudent.getId(), newStudent);
    }

    public Student read(long id) {
        if (id >= 0) {
            Student student = studentMap.get(id);
            checkNotFound(student);
            return student;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void checkIllegal(String name, int age) {
        if (name == null || age > 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFound(Student student) {
        if (!studentMap.containsValue(student)) {
            throw new IllegalArgumentException();
        }
    }

}
