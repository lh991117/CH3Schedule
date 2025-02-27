package com.example.schedule.Dto;

import lombok.Getter;

@Getter
public class ScheduleWithUsernameResponseDto {
    private final String todoTitle;
    private final String todoContent;
    private final String username;

    public ScheduleWithUsernameResponseDto(String todoTitle, String todoContent, String username){
        this.todoTitle=todoTitle;
        this.todoContent=todoContent;
        this.username=username;
    }
}
