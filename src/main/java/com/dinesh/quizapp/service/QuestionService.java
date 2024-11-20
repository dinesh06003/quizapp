package com.dinesh.quizapp.service;



import com.dinesh.quizapp.model.Question;
import com.dinesh.quizapp.repo.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        try {
            Question savedQuestion = questionRepo.save(question);
            if(savedQuestion.getId() != null){
                return new ResponseEntity<>("success", HttpStatus.CREATED);

            }else {
                return new ResponseEntity<>("not saved", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("error occurred while adding question: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            if(questionRepo.existsById(id)){
                questionRepo.deleteById(id);
                return new ResponseEntity<>("Question deleted successfully: " +id, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error occurred while deleting question: "+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(int id, Question updatedQuestion) {
        try {
            Optional<Question> existingQuestionOpt = questionRepo.findById(id);
            if(existingQuestionOpt.isPresent()){
                Question existingQuestion = getQuestion(updatedQuestion, existingQuestionOpt);

                questionRepo.save(existingQuestion);
                return new ResponseEntity<>("Question updated successfully", HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error occured while updating question: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Question getQuestion(Question updatedQuestion, Optional<Question> existingQuestionOpt) {
        Question existingQuestion = existingQuestionOpt.get();
        existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
        existingQuestion.setCategory(updatedQuestion.getCategory());
        existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
        existingQuestion.setOption1(updatedQuestion.getOption1());
        existingQuestion.setOption2(updatedQuestion.getOption2());
        existingQuestion.setOption3(updatedQuestion.getOption3());
        existingQuestion.setOption4(updatedQuestion.getOption4());
        return existingQuestion;
    }
}
