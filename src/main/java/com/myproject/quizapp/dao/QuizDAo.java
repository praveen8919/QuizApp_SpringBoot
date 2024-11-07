package com.myproject.quizapp.dao;


import com.myproject.quizapp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAo extends JpaRepository<Quiz,Integer> {

}
