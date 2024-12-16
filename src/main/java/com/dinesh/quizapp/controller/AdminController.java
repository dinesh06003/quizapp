package com.dinesh.quizapp.controller;

import com.dinesh.quizapp.model.Question;
import com.dinesh.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @PostMapping("add-question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete-question/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update-question/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question updatedQuestion){
        return questionService.updateQuestion(id, updatedQuestion);
    }
}