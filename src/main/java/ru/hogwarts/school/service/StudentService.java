package ru.hogwarts.school.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        }
        return null;
    }

    public Student update(Student student) {
        Long id = student.getId();
        Optional<Student> student1 = studentRepository.findById(id);
        if(student1.isPresent()) {
            studentRepository.save(student);
            return student1.get();
        }
        return null;
    }

    public Student read(long id) {
        Student student = studentRepository.getReferenceById(id);
        return student;
    }

    public List<Student> getAll() {
        List <Student> stList = studentRepository.findAll();
        return stList;
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int ageMin, int ageMax) {
        if (ageMin<ageMax) {
            return studentRepository.findByAgeBetween(ageMin, ageMax);
        }else{
            return null;
        }
    }

//    public Faculty getFaculty(Long id) {
//        Student student = read(id);
//        return student.getFaculty();
//    }
}
