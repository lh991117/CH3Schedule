package com.example.schedule.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @Size(min = 1, max = 10)
    private final String username;
    @NotBlank
    private final String password;
    @Email
    private final String email;

    public SignUpRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
