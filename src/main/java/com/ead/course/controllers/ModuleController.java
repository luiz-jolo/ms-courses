package com.ead.course.controllers;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<ModuleModel>> getModules(){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.getAllModules());
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModuleModel> getModuleById(@PathVariable UUID moduleId){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.getOneModule(moduleId));
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<ModuleModel> updateModule(@PathVariable UUID moduleId,
                                                    @RequestBody @Valid ModuleRecordDto moduleRecordDto){
        var module = moduleService.getOneModule(moduleId);
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.update(moduleRecordDto, module));
    }
}
