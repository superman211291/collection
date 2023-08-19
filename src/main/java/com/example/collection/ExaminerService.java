package com.example.collection;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
