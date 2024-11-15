package com.dinesh.quizapp.service;



import com.dinesh.quizapp.model.Question;
import com.dinesh.quizapp.repo.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

            List<Question> categoryQuestions = questionRepo.findByCategory(category);
            if(!categoryQuestions.isEmpty()){
                return new ResponseEntity<>(categoryQuestions, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
        }

    public ResponseEntity<String> addQuestion(Question question) {
        Question savedQuestion = questionRepo.save(question);
        if(savedQuestion.getId() != 0){
            return new ResponseEntity<>("success", HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>("not saved", HttpStatus.BAD_REQUEST);
        }
    }
}
