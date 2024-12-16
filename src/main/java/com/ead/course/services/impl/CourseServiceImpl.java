package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;
    final ModuleRepository moduleRepository;
    final LessonRepository lessonRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ModuleRepository moduleRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
    }

    @Transactional
    @Override
    public void delete(CourseModel courseModel) {
        List<ModuleModel> moduleModelsList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
        if(!moduleModelsList.isEmpty()) {
            for(ModuleModel moduleModel : moduleModelsList) {
                List<LessonModel> lessonModelsList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
                if (!lessonModelsList.isEmpty()){
                    lessonRepository.deleteAll(lessonModelsList);
                }
            }
            moduleRepository.deleteAll(moduleModelsList);
        }
        courseRepository.delete(courseModel);
    }
}
