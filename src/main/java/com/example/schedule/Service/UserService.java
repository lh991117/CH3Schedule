package com.example.schedule.Service;

import com.example.schedule.Dto.LoginRequestDto;
import com.example.schedule.Dto.LoginResponseDto;
import com.example.schedule.Dto.SignUpResponseDto;
import com.example.schedule.Dto.UserResponseDto;
import com.example.schedule.Entity.User;
import com.example.schedule.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //유저 회원가입
    public SignUpResponseDto signUp(String username, String password, String email) {
        User user=new User(username, email, password);
        User savedUser=userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    //id를 통한 유저 검색
    public UserResponseDto findById(Long id) {
        Optional<User> user=userRepository.findById(id);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID에는 정보가 존재하지 않습니다!");
        }

        User findUser=user.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    //id를 통해서 해당 유저의 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        //해당 id에 정보가 있는지 검토
        User findUser=userRepository.findByIdOrElseThrow(id);

        //비밀번호가 틀릴 경우 해당 예외로 던진다.
        if(!findUser.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        //변경된 비밀번호로 비밀번호를 저장한다.
        findUser.updatePassword(newPassword);
    }

    //id를 통한 유저 삭제
    public void deleteUser(Long id){
        User findUser=userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }

    //회원가입
    @Transactional
    public String registerUser(String email, String password, String username) {
        if(userRepository.findUserByEmail(email).isPresent()){
            return "Already use Email";
        }

        User user = new User(username, email, password);
        userRepository.save(user);

        return "Success";
    }


    //이메일을 통해서 유저 로그인
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletRequest httpRequest) {
        User user=userRepository.findUserByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email or password"));

        //세션에 사용자 ID 저장
        HttpSession session=httpRequest.getSession(true);
        session.setAttribute("user", user.getId());

        //세션 ID를 쿠키로 설정
        return new LoginResponseDto(user.getId());
    }

    //로그아웃(세션과 쿠키 삭제)
    public void logout(HttpServletRequest httpRequest) {
        //현제 세션을 가져온다.(없으면 null)
        HttpSession session=httpRequest.getSession(false);
        if(session!=null){
            //세션을 삭제한다.
            session.invalidate();
        }
    }
}
