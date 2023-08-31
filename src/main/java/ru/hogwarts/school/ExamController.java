package ru.hogwarts.school;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/test")
public class ExamController {


    private final ExaminerService examinerService;


    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{id}")
    public String getTestQuestions(@PathVariable("id") String departmentId) {
        Collection<Question> questions;
        try {
            questions = examinerService.getQuestions(Integer.parseInt(departmentId));
        } catch (BadRequest e){
            return "Не корректные данные!";
        }
        return questions.toString();
    }




}
