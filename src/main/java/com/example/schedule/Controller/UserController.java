package com.example.schedule.Controller;

import com.example.schedule.Dto.UserRequestDto;
import com.example.schedule.Dto.UserResponseDto;
import com.example.schedule.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor//초기화 되지않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자 생성
public class UserController {
    private final UserService userService;

    //유저 CRUD의 C
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto=
                userService.signUp(
                        userRequestDto.getUsername(),
                        userRequestDto.getPassword(),
                        userRequestDto.getEmail()
                );

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
