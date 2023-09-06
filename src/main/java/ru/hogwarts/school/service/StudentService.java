package ru.hogwarts.school.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    //private final Map<Long, Student> studentMap = new HashMap<>();
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        // checkIllegal(student.getName(), student.getAge());
        studentRepository.save(student);//.put(student.getId(), student);
        return student;
    }

    public Student delete(Long id) {
        Student student = studentRepository.getReferenceById(id);
        studentRepository.deleteById(id);
        return student;
    }

    public Student update(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Student read(long id) {
        Student student = studentRepository.getReferenceById(id);
        return student;
    }

    public List<Student> getAll() {
        List <Student> stList = studentRepository.findAll();
        return stList;
    }

    public Collection<Student> findByAge(Long age) {
        return studentRepository.findByAge(age);
    }
}
