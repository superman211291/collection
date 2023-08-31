package ru.hogwarts.school;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
public class JavaController {

    private final QuestionService questionService;


    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Привет!";
    }

    @GetMapping("/java/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        try {
            questionService.add(question, answer);
        } catch (QuestionAlreadyAddedException e) {
            return "Такой вопрос уже есть!";
        } catch (DataException e) {
            return "Ведены некорректные символы!";
        }
        return question + "\n" + "Вопрос добавлен!";
    }

    @GetMapping("/java/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {

        Question question1 = new Question(question, answer);
        try {
            questionService.remove(question1);
        } catch (QuestionNotFoundException e) {
            return "Вопрос не найден!";
        } catch (DataException e) {
            return "Ведены некорректные символы!";
        }
        return question + "\n" + "Вопрос удален!";
    }

    @GetMapping("/java")
    public String getAllQuestions() {
        return questionService.getAll().toString();
    }


}
