package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    //private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty delete(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.deleteById(id);
            return faculty.get();
        }
        return null;
    }

    public Faculty update(Faculty faculty) {
        Long id = faculty.getId();
        Optional<Faculty> faculty1 = facultyRepository.findById(id);
        if (faculty1.isPresent()) {
            facultyRepository.save(faculty);
            return faculty1.get();
        }
        return null;
    }

    public Faculty read(long id) {
        Faculty faculty = facultyRepository.getReferenceById(id);
        return faculty;

    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }


    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrNameIgnoreCase(String colorOrName) {
        return facultyRepository.ByColorOrNameIgnoreCase(colorOrName);
    }

//    public Collection<Student> getFacultyStudents(Long id) {
//        Faculty faculty = read(id);
//        return faculty.getStudents();
//    }
}
