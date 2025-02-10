package com.example.schedule.Dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private final String todoTitle;
    private final String todoContent;

    public UpdateScheduleRequestDto(String todoTitle, String todoContent){
        this.todoTitle=todoTitle;
        this.todoContent=todoContent;
    }
}
