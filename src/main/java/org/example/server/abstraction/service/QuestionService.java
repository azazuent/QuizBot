package org.example.server.abstraction.service;

import org.example.server.repository.Question;
import org.example.server.repository.Score;

public interface QuestionService {

    QuestionDto getById(Long id);

    Long addQuestion(AddQuestionDto addQuestionDto);

    String getRandomQuestion();

    String getAnswerByQuestion(String question);

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
