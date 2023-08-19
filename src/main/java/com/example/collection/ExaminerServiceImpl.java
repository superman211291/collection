package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount>questionService.getAll().size()){
            throw new BadRequest();
        }
        List<Question> questions = new ArrayList<>();
        Question question;
        int count = 0;
        while (count < amount) {
            question = questionService.getRandomQuestion();
            if (questions.size() == 0) {
                questions.add(question);
                count++;
            } else if (!questions.contains(question)) {
                questions.add(question);
                count++;
            }
        }
        return questions;
    }
}
