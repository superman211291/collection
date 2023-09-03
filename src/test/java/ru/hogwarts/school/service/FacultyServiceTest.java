package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;

class FacultyServiceTest {
    private FacultyService facultyService;
    private Faculty faculty;
    @BeforeEach
    public void setUp(){
        facultyService = new FacultyService();
        faculty = new Faculty(0l,"Grifindor","Red");
        facultyService.create(faculty);
    }
    @Test
    void create() {
        Faculty faculty1 =new Faculty(1l,"Slizeryne","green");
        facultyService.create(faculty1);
        Assertions.assertEquals(facultyService.read(1l),faculty1);
    }

    @Test
    void delete() {
        Faculty faculty1 = facultyService.delete(0l);
        Assertions.assertEquals(faculty, faculty1);
    }

    @Test
    void update() {
        Faculty faculty1 = new Faculty(0l,"Slizeryne","green");
        facultyService.update(faculty1);
        Assertions.assertEquals(faculty1,facultyService.read(0l));
    }

    @Test
    void read() {
        Assertions.assertEquals(faculty,facultyService.read(0l));
    }

    @Test
    void getAll() {
        Faculty faculty1 = new Faculty(1l,"Slizeryne","green");
        facultyService.create(faculty1);
        List<Faculty> facultyArrayList = new ArrayList<>();
        facultyArrayList.add(faculty);
        facultyArrayList.add(faculty1);
        Assertions.assertEquals(facultyArrayList.get(0), new ArrayList<>(facultyService.getAll()).get(0));
        Assertions.assertEquals(facultyArrayList.get(1), new ArrayList<>(facultyService.getAll()).get(1));

    }

    @Test
    void findByColor() {
        Faculty faculty1 = new Faculty(1l,"Slizeryne","green");
        facultyService.create(faculty1);
        Assertions.assertEquals(faculty1, new ArrayList<>(facultyService.findByColor("green")).get(0));
    }

}