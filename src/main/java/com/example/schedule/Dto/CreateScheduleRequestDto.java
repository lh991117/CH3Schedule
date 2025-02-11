package com.example.schedule.Dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {
    //할일 제목 글자를 10까지 설정
    @Size(min = 1, max = 10)
    private final String todoTitle;
    //할일 내용은 사이즈 미정(길이가 길 수 있기 때문에)
    private final String todoContent;
    private final String username;

    public CreateScheduleRequestDto(String todoTitle, String todoContent, String username){
        this.todoTitle=todoTitle;
        this.todoContent=todoContent;
        this.username=username;
    }
}
