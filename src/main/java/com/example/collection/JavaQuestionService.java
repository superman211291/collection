package com.example.collection;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questions = new HashSet<>();
    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question,answer);
        questions.add(question1);
        return question1;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        Question question1 = new Question(question.getQuestion(),question.getAnswer());
        questions.remove(question);
        return question1;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
