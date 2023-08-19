package com.example.collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService questionService;


    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Привет!";
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam String Question, @RequestParam String Answer) {
        checkData(Question,Answer);
        Question question = new Question(StringUtils.capitalize(Question), StringUtils.capitalize(Answer));
        try {
            questionService.add(question);
        } catch (EmployeeStorageIsFullException e) {
            return "ArrayIsFull";
        } catch (EmployeeAlreadyAddedException e) {
            return "QuestionAlreadyAdded";
        }
        return question.toString() + "\n" +"Is added!";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        checkData(firstName,lastName);
        Question employee = new Question(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), 1, 0.0);
        try {
            questionService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/find")
    public String findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        checkData(firstName,lastName);
        Question employee;
        try {
            employee = questionService.searchEmployee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
        } catch (EmployeeNotFoundException e) {
            return "EmployeeNotFound";
        }
        return employee.toString();
    }

    @GetMapping("/getAll")
    public String getAllEmployee() {
        return questionService.getAll().toString();
    }

    private void checkData(String firstName, String lastName){
        char[] russianLetters = new char[64];
        for (int i = 0; i < 64; i++) {
            russianLetters[i]= (char)(1040+i);
        }
        if(     !StringUtils.containsOnly(firstName, russianLetters) ||
                !StringUtils.containsOnly(lastName, russianLetters) ){
             throw new DataException();
        }


    }


}
