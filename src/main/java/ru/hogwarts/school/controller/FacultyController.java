package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyNotFoundException;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentNotFoundException;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.read(id);
        if (faculty == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.update(faculty);
        if (faculty1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteStudent(@PathVariable Long id) {
        Faculty faculty1 = facultyService.delete(id);
        if (faculty1 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @GetMapping("/findColor/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }

    @GetMapping("/findColor/{colorOrName}")
    public ResponseEntity<Collection<Faculty>> getFacultyByColorOrNameIgnoreCase(@PathVariable String colorOrName) {
        return ResponseEntity.ok(facultyService.findByColorOrNameIgnoreCase(colorOrName));
    }

//    @GetMapping("/getStudents/{id}")
//    public ResponseEntity<Collection<Student>> getFacultyStudents(@PathVariable Long id) {
//        return ResponseEntity.ok(facultyService.getFacultyStudents(id));
//    }
}
