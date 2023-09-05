package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
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
        Faculty faculty = facultyRepository.getReferenceById(id);
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Faculty update(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty read(long id) {
            Faculty faculty = facultyRepository.getReferenceById(id);
            return faculty;

    }

    public Collection<Faculty> getAll(){
        return facultyRepository.findAll();
    }



    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}
