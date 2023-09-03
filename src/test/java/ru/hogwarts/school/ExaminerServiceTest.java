//
//package ru.hogwarts.school;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//class ExaminerServiceTest {
//
//    @Mock
//    private QuestionService questionService;
//
//    @InjectMocks
//    private ExaminerServiceImpl examinerService;
//
//    private List<Question> questions;
//    @BeforeEach
//    public void setUp(){
//        questions = new ArrayList<>();
//        questions.add(new Question("Илья","Строев"));
//        questions.add(new Question("Федор", "Петров"));
//        questions.add(new Question("Максим", "Каширин"));
//    }
//    @Test
//    void getQuestions() {
//        Mockito.when(questionService.getAll()).thenReturn(questions);
//        Mockito.when(questionService.getRandomQuestion()).thenReturn(questions.get(0),questions.get(1),questions.get(2));
//        Collection<Question> questions1 = examinerService.getQuestions(2);
//        assertEquals(2,questions1.size());
//    }
//
//    @Test
//    void shouldThrowBadRequestException() {
//        Mockito.when(questionService.getAll()).thenReturn(questions);
//        assertThrows(BadRequest.class, ()->  examinerService.getQuestions(4));
//    }
//
//}
