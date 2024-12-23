package com.ead.course.services;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.models.ModuleModel;

import java.util.Optional;

public interface ModuleService {

    void delete(ModuleModel module);

    ModuleModel save(ModuleRecordDto moduleRecordDto);
}
