package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

class StudentServiceTest {

    private StudentService studentService;
    private StudentRepository studentRepository;
    private Student student;
   @BeforeEach
    public void setUp(){
        studentService = new StudentService(studentRepository);
        student = new Student(1l,"Ilya",31);
        studentService.create(student);
    }
    @Test
    void create() {
        Student student1 = studentService.create(new Student(1l,"Maria",27));
        Assertions.assertEquals(studentService.read(1l),student1);
    }

    @Test
    void delete() {
       Student student1 = studentService.delete(0l);
       Assertions.assertEquals(student, student1);
    }

    @Test
    void update() {
       Student student1 = new Student(0l,"Maria",27);
        studentService.update(student1);
       Assertions.assertEquals(student1,studentService.read(0l));
    }

    @Test
    void read() {
       Assertions.assertEquals(student,studentService.read(0l));
    }

    @Test
    void getAll() {
        Student student1 = new Student(1l,"Maria",27);
        studentService.create(student1);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        Assertions.assertEquals(studentList.get(0), new ArrayList<>(studentService.getAll()).get(0));
        Assertions.assertEquals(studentList.get(1), new ArrayList<>(studentService.getAll()).get(1));

    }

    @Test
    void findByAge() {
        Student student1 = new Student(1l,"Maria",27);
        studentService.create(student1);
        Assertions.assertEquals(student1, new ArrayList<>(studentService.findByAge(27)).get(0));
    }
}