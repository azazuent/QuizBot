package org.example.server.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuestionTag {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public QuestionTag(){};

    public QuestionTag(
            Long id,
            Long questionId,
            Long tagId
    ){
        this.questionId = questionId;
        this.tagId = tagId;
    }

    private Long questionId;

    private Long tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
