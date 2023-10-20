package org.example.server.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.server.repository.entity.Question;
import org.example.server.repository.entity.QuestionTag;
import org.example.server.repository.entity.Tag;
import org.example.server.repository.repo.QuestionRepo;
import org.example.server.repository.entity.Score;
import org.example.server.repository.repo.QuestionTagRepo;
import org.example.server.repository.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    TagRepo tagRepo;

    @Autowired
    QuestionTagRepo questionTagRepo;


    public Question getRandomQuestion() {
        return questionRepo.getRandomQuestion();
    }

    public Question getRandomQuestionByTag(String tag) {
        return questionRepo.getRandomQuestionByTag(tag);
    }

    public void addQuestion(Question question){
        questionRepo.save(question);
    }

    public void addTag(Tag tag){
        tagRepo.save(tag);
    }

    public void bindTag(String tag, String question){
        Long tagId = tagRepo.getTagId(tag);
        System.out.println(tagId);
        if (tagId == null){
            tagRepo.save(new Tag(null, tag));
        }
        QuestionTag questionTag = new QuestionTag(0L,
                questionRepo.getQuestionId(question),
                tagRepo.getTagId(tag));
        questionTagRepo.save(questionTag);
    }


}
