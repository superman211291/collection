package com.example.collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        checkData(question, answer);
        Question question1 = new Question(question, answer);
        checkContains(question1);
        questions.add(question1);
        return question1;
    }

    private void checkContains(Question question1) {
        if (questions.contains(question1)) {
            throw new QuestionAlreadyAddedException();
        }
    }

    @Override
    public Question add(Question question) {
        checkData(question.getQuestion(), question.getAnswer());
        checkContains(question);
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        checkData(question.getQuestion(), question.getAnswer());
        checkNotContains(question);
        Question question1 = new Question(question.getQuestion(), question.getAnswer());
        questions.remove(question);
        return question1;
    }

    private void checkNotContains(Question question1) {
        if (!questions.contains(question1)) {
            throw new QuestionNotFoundException();
        }
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

    private void checkData(String firstName, String lastName) {
        char[] russianLetters = new char[64];
        for (int i = 0; i < 64; i++) {
            russianLetters[i] = (char) (1040 + i);
        }
        if (!StringUtils.containsOnly(firstName, russianLetters) ||
                !StringUtils.containsOnly(lastName, russianLetters)) {
            throw new DataException();
        }


    }
}
