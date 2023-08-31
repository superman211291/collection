package ru.hogwarts.school;

import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();


}
