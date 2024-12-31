package com.ead.course.services;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface LessonService {
    LessonModel save(LessonRecordDto lessonRecordDto, ModuleModel module);
}
