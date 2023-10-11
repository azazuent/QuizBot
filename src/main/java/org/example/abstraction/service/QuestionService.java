package org.example.abstraction.service;

import org.example.repository.Question;
import org.example.repository.QuestionTag;
import org.example.repository.Score;
import org.example.repository.Tag;
import org.springframework.stereotype.Service;

public interface QuestionService {

    QuestionDto getById(Long id);

    Long addQuestion(AddQuestionDto addQuestionDto);

    String getRandomQuestion();

    String getRandomQuestionByTag(String tag);

    record QuestionDto(
            Long id,
            String question,
            String answer
    ){
        public static QuestionDto fromDbEntity(Question question){
            return new QuestionDto(
                    question.getId(),
                    question.getQuestion(),
                    question.getAnswer());
        }
    }

    record AddQuestionDto(
            String question,
            String answer
    ){
        public static Question toDbEntity(AddQuestionDto addQuestionDto){
            return new Question(
                    null,
                    addQuestionDto.question,
                    addQuestionDto.answer
            );
        }
    }

    record AnswerQuestionDto(
            Long questionId,
            String answer
    ){ }

    record AddScoreDto(
            Long questionId,
            Long userId
    ) {
        public static Score toDbEntity(AddScoreDto addScoreDto) {
            return new Score(
                    null,
                    addScoreDto.questionId(),
                    addScoreDto.userId()
            );
        }
    }
}
