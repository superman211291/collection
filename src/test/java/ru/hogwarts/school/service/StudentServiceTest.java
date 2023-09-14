package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    private Student student;
    private Optional<Student> s;

    @BeforeEach
    void setUp() {
        student = new Student(1l, "Maria", 27);
        s = Optional.of(student);
    }

    @Test
    void create() {
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Student student2 = studentService.create(student);
        Mockito.verify(studentRepository).save(student);
        Assertions.assertEquals(student, student2);
    }

    @Test
    void delete() {
        Mockito.when(studentRepository.findById(1l)).thenReturn(s);
        Student student2 = studentService.delete(1l);
        Mockito.verify(studentRepository).deleteById(1l);
        Mockito.verify(studentRepository).findById(1l);
        Assertions.assertEquals(student, student2);
    }

    @Test
    void update() {
        Mockito.when(studentRepository.findById(1l)).thenReturn(s);
        Student student1 = studentService.update(student);
        Mockito.verify(studentRepository).save(student);
        Mockito.verify(studentRepository).findById(1l);
        Assertions.assertEquals(student,student1);
    }

    @Test
    void read() {
        Mockito.when(studentRepository.getReferenceById(0l)).thenReturn(student);
        Student student2 = studentService.read(0l);
        Mockito.verify(studentRepository).getReferenceById(0l);
        Assertions.assertEquals(student, student2);
    }

    @Test
    void getAll() {
        Student student1 = new Student(1l, "Ilya", 31);
        studentService.create(student1);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        Assertions.assertEquals(studentList.get(0), new ArrayList<>(studentService.getAll()).get(0));
        Mockito.verify(studentRepository).findAll();
        Assertions.assertEquals(studentList.get(1), new ArrayList<>(studentService.getAll()).get(1));

    }

    @Test
    void findByAge() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Mockito.when(studentRepository.findByAge(27)).thenReturn(studentList);
        Assertions.assertEquals(student, new ArrayList<>(studentService.findByAge(27)).get(0));
        Mockito.verify(studentRepository).findByAge(27);
    }
}