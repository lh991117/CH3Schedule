package com.example.schedule.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    //사용자가 입력한 이메일 주소
    @Email
    private final String email;

    //사용자가 입력한 비밀번호
    @NotBlank
    private final String password;
}
