package com.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ModuleRecordDto(

        @NotBlank
        String title,

        @NotBlank
        String description
) {
}