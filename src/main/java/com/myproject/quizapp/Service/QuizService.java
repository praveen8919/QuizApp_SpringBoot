package com.myproject.quizapp.Service;

import com.myproject.quizapp.Entity.Question;
import com.myproject.quizapp.Entity.QuestionWrapper;
import com.myproject.quizapp.Entity.Response;
import com.myproject.quizapp.dao.QuestionDAO;
import com.myproject.quizapp.Entity.Quiz;
import com.myproject.quizapp.dao.QuizDAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDAo quizdao;
    @Autowired
    private QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category,int numQuestions, String title){
        List<Question> questions=questionDAO.findQuestionsByCategory(category,numQuestions);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizdao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz=quizdao.findById(id);
        List<Question> questionsfromdb=quiz.get().getQuestions();
        List<QuestionWrapper> questionforUser=new ArrayList<>();

        for(Question q:questionsfromdb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionforUser.add(qw);
        }

        return new ResponseEntity<>(questionforUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
        Quiz quiz=quizdao.findById(id).get();
        List<Question> questions= quiz.getQuestions();
        int answer=0;
        int i=0;
        for(Response r:response){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())){
                answer++;
            }
            i++;
        }
        return new ResponseEntity<>(answer,HttpStatus.OK);
    }
}
