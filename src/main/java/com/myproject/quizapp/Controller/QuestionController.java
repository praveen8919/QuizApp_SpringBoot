package com.myproject.quizapp.Controller;


import com.myproject.quizapp.Entity.Question;
import com.myproject.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("editQuestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
        return questionService.updateQuestion(id,question);
    }




}
