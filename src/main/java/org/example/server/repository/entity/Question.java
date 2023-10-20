package org.example.server.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Question(){}

    public Question(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    private String question;

    private String answer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
