package com.ead.course.controllers;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/modules/{moduleId}/lessons/{lessonId}")
    ResponseEntity<LessonModel> updateLesson(@PathVariable UUID moduleId,
                                             @PathVariable UUID lessonId,
                                             @RequestBody LessonRecordDto lessonRecordDto) {

    }

}
