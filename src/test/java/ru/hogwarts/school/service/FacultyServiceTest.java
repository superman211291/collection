package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService facultyService;
    private Faculty faculty;
    private Optional<Faculty> f;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty(0l, "Grifindor", "Red");
        f = Optional.of(faculty);
    }

    @Test
    void create() {
        Mockito.when(facultyRepository.save(faculty)).thenReturn(faculty);
        Faculty faculty1 = facultyService.create(faculty);
        Assertions.assertEquals(faculty, faculty1);
        Mockito.verify(facultyRepository).save(faculty);
    }

    @Test
    void delete() {
        Mockito.when(facultyRepository.findById(0l)).thenReturn(f);
        Faculty faculty1 = facultyService.delete(0l);
        Mockito.verify(facultyRepository).deleteById(0l);
        Mockito.verify(facultyRepository).findById(0l);
        Assertions.assertEquals(faculty, faculty1);
    }

    @Test
    void update() {
        Faculty faculty1 = new Faculty(0l, "Slizeryne", "green");
        Optional<Faculty> f1 = Optional.of(faculty1);
        Mockito.when(facultyRepository.findById(0l)).thenReturn(f1);
        Faculty faculty2 = facultyService.update(faculty1);
        Assertions.assertEquals(faculty1, faculty2);
        Mockito.verify(facultyRepository).save(faculty1);
        Mockito.verify(facultyRepository).findById(0l);
    }

    @Test
    void read() {
        Mockito.when(facultyRepository.getReferenceById(0l)).thenReturn(faculty);
        Assertions.assertEquals(faculty, facultyService.read(0l));
        Mockito.verify(facultyRepository).getReferenceById(0l);
    }

    @Test
    void getAll() {
        Faculty faculty1 = new Faculty(1l, "Slizeryne", "green");
        List<Faculty> facultyArrayList = new ArrayList<>();
        facultyArrayList.add(faculty);
        facultyArrayList.add(faculty1);
        Mockito.when(facultyRepository.findAll()).thenReturn(facultyArrayList);
        Assertions.assertEquals(facultyArrayList.get(0), new ArrayList<>(facultyService.getAll()).get(0));
        Mockito.verify(facultyRepository).findAll();
        Assertions.assertEquals(facultyArrayList.get(1), new ArrayList<>(facultyService.getAll()).get(1));

    }

    @Test
    void findByColor() {
        Faculty faculty1 = new Faculty(1l, "Slizeryne", "green");
        List<Faculty> facultyArrayList = new ArrayList<>();
        facultyArrayList.add(faculty1);
        Mockito.when(facultyRepository.findByColor("green")).thenReturn(facultyArrayList);
        Assertions.assertEquals(faculty1, new ArrayList<>(facultyService.findByColor("green")).get(0));
        Mockito.verify(facultyRepository).findByColor("green");
    }

}