package com.ead.course.controllers;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    final ModuleService moduleService;
    final CourseService courseService;

    public ModuleController(ModuleService moduleService, CourseService courseService) {
        this.moduleService = moduleService;
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveModule(@RequestBody @Valid ModuleRecordDto moduleRecordDto){
        if(courseService.findById(moduleRecordDto.courseId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Course doesn't exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleRecordDto));
    }

}
