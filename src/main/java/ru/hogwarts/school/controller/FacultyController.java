package ru.hogwarts.school.controller;

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

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        try {
            Faculty faculty = facultyService.read(id);
            return ResponseEntity.ok(faculty);
        }catch (IllegalArgumentException | FacultyNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty(){
        return ResponseEntity.ok(facultyService.getAll());
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.create(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
        try {
            Faculty faculty1 = facultyService.update(faculty);
            return ResponseEntity.ok(faculty1);
        }catch (IllegalArgumentException | FacultyNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("{id}")
    public Faculty deleteStudent(@PathVariable Long id){
        return facultyService.delete(id);
    }

    @GetMapping("/findColor/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }
}
