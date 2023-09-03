package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> studentMap = new HashMap<>();

    public Student create(Student student) {
       // checkIllegal(student.getName(), student.getAge());
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student delete(Long id) {
        return studentMap.remove(id);
    }

    public Student update(Student student) {
        //checkIllegal(student.getName(), student.getAge());
        //checkNotFound(student);
        return studentMap.put(student.getId(), student);
    }

    public Student read(long id) {
       // if (id >= 0) {
            Student student = studentMap.get(id);
            checkNotFound(student);
            return student;
        //} else {
         //   throw new IllegalArgumentException();
        //}
    }

    public Collection<Student> getAll(){
        return studentMap.values();
    }

    private void checkIllegal(String name, int age) {
        if (name == null || age > 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFound(Student student) {
        if (studentMap.get(student.getId())==null) {
            throw new StudentNotFoundException();
        }
    }

    public Collection<Student> findByAge(Long age) {
        return studentMap.values().stream()
                .filter(student -> student.getAge()==age)
                .collect(Collectors.toList());
    }
}
