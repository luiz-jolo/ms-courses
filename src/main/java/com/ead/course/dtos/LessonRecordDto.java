package com.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record LessonRecordDto(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String videoUrl
) {
}
