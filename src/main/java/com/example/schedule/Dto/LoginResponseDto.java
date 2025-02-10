package com.example.schedule.Dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long id;

    public LoginResponseDto(long id) {
        this.id = id;
    }
}
