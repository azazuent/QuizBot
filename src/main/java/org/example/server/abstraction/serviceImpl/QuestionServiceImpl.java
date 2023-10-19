package org.example.server.abstraction.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.example.server.abstraction.service.QuestionService;
import org.example.server.repository.QuestionRepo;
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

    public String getAnswerByQuestion(String question){
        return questionRepo.getAnswer(question);
    }

    @Override
    public String getRandomQuestionByTag(String tag) {
        return questionRepo.getRandomQuestionByTag(tag);
    }

}
