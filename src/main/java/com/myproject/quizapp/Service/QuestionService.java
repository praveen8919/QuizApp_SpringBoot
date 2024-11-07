package com.myproject.quizapp.Service;

import com.myproject.quizapp.Entity.Question;
import com.myproject.quizapp.dao.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question){
        questionDAO.save(question);
        try{
            return new ResponseEntity<>(question,HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(question,HttpStatus.CREATED);
    }

    public ResponseEntity<Question> updateQuestion(int id, Question question){
        Question existingQuestion = questionDAO.findById(id)
                .orElseThrow(()-> new RuntimeException("Question not found with id: "+id));


        if(question.getCategory()!=null){
            existingQuestion.setCategory(question.getCategory());
        }
        if(question.getQuestionTitle()!=null){
            existingQuestion.setQuestionTitle(question.getQuestionTitle());
        }
        if(question.getOption1()!=null){
            existingQuestion.setOption1(question.getOption1());
        }
        if(question.getOption2()!=null){
            existingQuestion.setOption2(question.getOption2());
        }
        if(question.getOption3()!=null){
            existingQuestion.setOption3(question.getOption3());
        }
        if(question.getOption4()!=null){
            existingQuestion.setOption4(question.getOption4());
        }
        if(question.getDifficultyLevel()!=null){
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
        }
        if(question.getRightAnswer()!=null){
            existingQuestion.setRightAnswer(question.getRightAnswer());
        }
        questionDAO.save(existingQuestion);

        try{
            return new ResponseEntity<>(existingQuestion,HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(existingQuestion,HttpStatus.CREATED);
    }


}
