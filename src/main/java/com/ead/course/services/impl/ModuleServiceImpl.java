package com.ead.course.services.impl;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {

    final ModuleRepository moduleRepository;
    final LessonRepository lessonRepository;
    final CourseRepository courseRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @Override
    public void delete(ModuleModel module) {
        List<LessonModel> lessonsModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
        if (!lessonsModelList.isEmpty()) {
            lessonRepository.deleteAll(lessonsModelList);
        }
        moduleRepository.delete(module);
    }

    @Override
    public ModuleModel save(ModuleRecordDto moduleRecordDto) {
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleRecordDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return moduleRepository.save(moduleModel);
    }

}
