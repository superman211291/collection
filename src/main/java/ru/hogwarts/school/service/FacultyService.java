package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    public Faculty create(Faculty faculty) {
        //checkIllegal(faculty.getName(), faculty.getColor());
        return facultyMap.put(faculty.getId(), faculty);

    }

    public Faculty delete(Long id) {
        return facultyMap.remove(id);
    }

    public Faculty update(Faculty faculty) {
        //checkIllegal(faculty.getName(), faculty.getColor());
        //checkNotFound(faculty);
        return facultyMap.put(faculty.getId(), faculty);
    }

    public Faculty read(long id) {
       // if (id >= 0) {
            Faculty faculty = facultyMap.get(id);
            checkNotFound(faculty);
            return faculty;
       // } else {
         //   throw new IllegalArgumentException();
       // }
    }

    public Collection<Faculty> getAll(){
        return facultyMap.values();
    }

    private void checkIllegal(String name, String color) {
        if (name == null || color == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFound(Faculty faculty) {
        if (facultyMap.get(faculty.getId())==null) {
            throw new FacultyNotFoundException();
        }
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyMap.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
