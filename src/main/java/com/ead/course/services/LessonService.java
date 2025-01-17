package com.ead.course.services;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {
    LessonModel save(LessonRecordDto lessonRecordDto, ModuleModel module);
    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);
    List<LessonModel> findAllLessonsIntoModule(UUID moduleId);
    LessonModel update(LessonRecordDto lessonRecordDto, LessonModel lessonModel);
    void delete(LessonModel lessonModel);
    Page<LessonModel> findAllLessonsIntoModule(Specification<LessonModel> spec, Pageable pageable);
}
