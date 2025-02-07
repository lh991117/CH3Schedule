package com.example.schedule.Dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String todoTitle;
    private final String todoContent;

    public ScheduleResponseDto(Long id, String todoTitle, String todoContent) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
    }
}
