package org.example.abstraction.service;

import org.example.repository.Question;
import org.example.repository.QuestionTag;
import org.example.repository.Tag;
import org.springframework.stereotype.Service;

public interface TagService {

    TagDto getById(Long id);

    Long addTag(AddTagDto addTagDto);

    public record TagDto(
            Long id,
            String tag
    ){
        public static TagDto fromDbEntity(Tag tag){
            return new TagDto(
                    tag.getId(),
                    tag.getTag());
        }
    }

    public record AddTagDto(
            String tag
    ){
        public static Tag toDbEntity(AddTagDto addTagDto){
            return new Tag(
                    null,
                    addTagDto.tag
            );
        }
    }

    public record BindTagDto(
            Long tagId,
            Long questionId
    ){
        public static QuestionTag toDbEntity(BindTagDto bindTagDto){
            return new QuestionTag(
                    null,
                    bindTagDto.questionId(),
                    bindTagDto.tagId());
        }
    }

//    public record GetScoreByTagDto(
//            String
//    )
}
