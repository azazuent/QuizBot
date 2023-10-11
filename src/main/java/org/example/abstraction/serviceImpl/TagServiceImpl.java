package org.example.abstraction.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.example.abstraction.service.QuestionService;
import org.example.abstraction.service.TagService;
import org.example.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepo tagRepo;

    @Override
    public TagDto getById(Long id){
        return TagDto.fromDbEntity(tagRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found")));
    }

    @Override
    public Long addTag(AddTagDto addTagDto){
        return tagRepo.save(addTagDto.toDbEntity(addTagDto)).getId();
    }
}
