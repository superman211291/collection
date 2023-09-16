package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentNotFoundException;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
            Student student = studentService.read(id);
            if (student == null) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
            Student student1 = studentService.update(student);
            if (student1==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(student1);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student student1 = studentService.delete(id);
        if (student1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student1);
    }

    @GetMapping("/findAge/{age}")
    public ResponseEntity<Collection<Student>> getStudentByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    @GetMapping("/findAgeBetween/{ageMin}{ageMax}")
    public ResponseEntity<Collection<Student>> getStudentByAgeBetween(@PathVariable int ageMin,@PathVariable int ageMax) {
        Collection<Student> students =studentService.findByAgeBetween(ageMin,ageMax);
        if (students==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }
//    @GetMapping("/getStudentFaculty/{id}")
//    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
//        return ResponseEntity.ok(studentService.getFaculty(id));
//    }



}
