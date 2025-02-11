package com.example.schedule.Dto;

import com.example.schedule.Entity.Schedule;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    @Size(min = 1, max = 10)
    private final String todoTitle;
    private final String todoContent;

    public ScheduleResponseDto(Long id, String todoTitle, String todoContent) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
    }

    //Entity를 Dto로 변환
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTodoTitle(), schedule.getTodoContent());
    }
}
