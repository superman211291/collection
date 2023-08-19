package com.example.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceTest {

    private QuestionService questionService;
    @BeforeEach
    public void setUp(){
        questionService = new JavaQuestionService();
    }

    @Test
    void addQuestion() {
        Question question = new Question("Илья","Строев");
        questionService.add(question);
        Collection<Question> resultQuestions = questionService.getAll();
        Assertions.assertEquals(question, new ArrayList<>(resultQuestions).get(0));


    }

    @Test
    void addQuestionStrings() {
        Question question = new Question("Илья","Строев");
        questionService.add("Илья","Строев");
        Collection<Question>resultQuestions = questionService.getAll();
        Assertions.assertEquals(question, new ArrayList<>(resultQuestions).get(0));
    }

    @Test
    void shouldThrowQuestionAlreadyAddedException() {
        Question employee = new Question("Илья","Строев");
        Question employee1 = new Question("Илья","Строев");
        questionService.add(employee);
        assertThrows(QuestionAlreadyAddedException.class, ()-> questionService.add(employee1));
    }

    @Test
    void removeQuestion() {
        Question question = new Question("Илья","Строев");
        questionService.add(question);
        Question resultEmployee = new ArrayList<>(questionService.getAll()).get(0);
        Assertions.assertEquals(question,resultEmployee);
        questionService.remove(new Question("Илья","Строев"));
        Collection<Question> resultList = questionService.getAll();
        Assertions.assertEquals(0,resultList.size());
    }

    @Test
    void shouldThrowQuestionNotFoundException() {
        Question question = new Question("Илья","Строев");
        questionService.add(question);
        assertThrows(QuestionNotFoundException.class, ()-> questionService.remove(new Question("Федор","Петров")));
    }

    @Test
    void shouldThrowQuestionDataException() {
        Question question = new Question("kjsdj","Строев");
        assertThrows(DataException.class, ()-> questionService.add(question));
    }

}