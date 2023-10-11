package org.example.abstraction.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.example.abstraction.service.QuestionService;
import org.example.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public QuestionDto getById(Long id){
        return QuestionDto.fromDbEntity(questionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question with id " + id + " not found")));
    }

    @Override
    public Long addQuestion(AddQuestionDto addQuestionDto){
        return questionRepo.save(addQuestionDto.toDbEntity(addQuestionDto)).getId();
    }

    @Override
    public String getRandomQuestion() {
        return questionRepo.getRandomQuestion();
    }

    @Override
    public String getRandomQuestionByTag(String tag) {
        return questionRepo.getRandomQuestionByTag(tag);
    }

}
