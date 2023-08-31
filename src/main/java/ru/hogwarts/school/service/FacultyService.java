package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    public Faculty create(String name, String color) {
        checkIllegal(name, color);
        Faculty faculty = new Faculty(name, color);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;

    }

    public Faculty delete(String name, String color) {
        checkIllegal(name, color);
        Faculty faculty = new Faculty(name, color);
        checkNotFound(faculty);
        return facultyMap.remove(faculty.getId());
    }

    public Faculty update(String oldName, String oldColor, String name, String color) {
        checkIllegal(oldName, oldColor);
        checkIllegal(name, color);
        Faculty faculty = new Faculty(oldName, oldColor);
        Faculty newFaculty = new Faculty(name, color);
        newFaculty.setId(faculty.getId());
        checkNotFound(faculty);
        return facultyMap.put(newFaculty.getId(), newFaculty);
    }

    public Faculty read(long id) {
        if (id >= 0) {
            Faculty faculty = facultyMap.get(id);
            checkNotFound(faculty);
            return faculty;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void checkIllegal(String name, String color) {
        if (name == null || color == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNotFound(Faculty faculty) {
        if (!facultyMap.containsValue(faculty)) {
            throw new IllegalArgumentException();
        }
    }
}
