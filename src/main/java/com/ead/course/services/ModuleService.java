package com.ead.course.services;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.models.ModuleModel;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    void delete(ModuleModel module);
    ModuleModel save(ModuleRecordDto moduleRecordDto);
    List<ModuleModel> getAllModules();
    ModuleModel getOneModule(UUID id);
    ModuleModel update(ModuleRecordDto moduleRecordDto, ModuleModel module);
}
