package com.example.schedule.Dto;

import lombok.Getter;

//User 응답처리(패스워드는 제외)
@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
