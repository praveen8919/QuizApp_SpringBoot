package com.myproject.quizapp.Controller;

import com.myproject.quizapp.Entity.QuestionWrapper;
import com.myproject.quizapp.Entity.Response;
import com.myproject.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQuestions, @RequestParam String title) {
        return quizService.createQuiz(category,numQuestions,title);
    }

    @GetMapping("getQuizQuestions/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
        return quizService.submitQuiz(id,response);
    }
}
