package com.example.schedule.Controller;

import com.example.schedule.Dto.SignUpRequestDto;
import com.example.schedule.Dto.SignUpResponseDto;
import com.example.schedule.Dto.UpdatePasswordRequestDto;
import com.example.schedule.Dto.UserResponseDto;
import com.example.schedule.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor//초기화 되지않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자 생성
public class UserController {
    private final UserService userService;

    //유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signupResponseDto=
                userService.signUp(
                        signUpRequestDto.getUsername(),
                        signUpRequestDto.getPassword(),
                        signUpRequestDto.getEmail()
                );

        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);
    }

    //id를 통한 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto userResponseDto=userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    //id를 통한 비밀번호 변경
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto) {
        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //id를 통한 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
