package com.ead.course.controllers;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import com.ead.course.specifications.SpecificationTemplate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class LessonController {

    final ModuleService moduleService;
    final LessonService lessonService;

    public LessonController(ModuleService moduleService, LessonService lessonService) {
        this.moduleService = moduleService;
        this.lessonService = lessonService;
    }

    @PostMapping("/modules/{moduleId}/lessons")
    ResponseEntity<Object> saveLesson(@PathVariable UUID moduleId,
                                      @RequestBody @Valid LessonRecordDto lessonRecordDto) {
        var module = moduleService.findModuleById(moduleId).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonRecordDto, module));
    };

    @GetMapping("/modules/{moduleId}/lessons")
    ResponseEntity<Page<LessonModel>> getAllLessons(@PathVariable UUID moduleId,
                                                    SpecificationTemplate.LessonSpec spec,
                                                    Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findAllLessonsIntoModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable));
    }

    @GetMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> getOneLesson(@PathVariable UUID moduleId,
                                               @PathVariable UUID lessonId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findLessonIntoModule(moduleId, lessonId));
    }

    @PutMapping("/modules/{moduleId}/lessons/{lessonId}")
    ResponseEntity<Object> updateLesson(@PathVariable UUID moduleId,
                                          @PathVariable UUID lessonId,
                                          @RequestBody LessonRecordDto lessonRecordDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.update(lessonRecordDto, lessonService.findLessonIntoModule(moduleId, lessonId).get()));
    }

    @DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
    ResponseEntity deleteLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonId) {
        lessonService.delete(lessonService.findLessonIntoModule(moduleId, lessonId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
    }
}
