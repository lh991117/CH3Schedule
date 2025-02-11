package com.example.schedule.Service;

import com.example.schedule.Config.PasswordEncoder;
import com.example.schedule.Dto.LoginRequestDto;
import com.example.schedule.Dto.LoginResponseDto;
import com.example.schedule.Dto.SignUpResponseDto;
import com.example.schedule.Dto.UserResponseDto;
import com.example.schedule.Entity.User;
import com.example.schedule.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final PasswordEncoder passwordEncoder;

    //유저 회원가입
    public SignUpResponseDto signUp(String username, String password, String email) {
        String hashedPassword = passwordEncoder.encode(password);//비밀번호 해싱
        User user=new User(username, email, hashedPassword);
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

//        String hashedPassword = passwordEncoder.encode(password);//비밀번호 해싱
        User user = new User(username, email, password);
        userRepository.save(user);

        return "Success";
    }


    //이메일을 통해서 유저 로그인
    public LoginResponseDto login(LoginRequestDto requestDto, HttpSession session, HttpServletResponse response) {

        User user=userRepository.findUserByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email or password"));

        //입력한 비밀번호와 DB에 저장된 암호화된 비밀번호 비교
//        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
//            System.out.println("error");
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
//        }

        //세션에 사용자 ID 저장
        session.setAttribute("user", user.getId());

        //세션 ID를 쿠키로 설정
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new LoginResponseDto(user.getId());
    }

    //로그아웃(세션과 쿠키 삭제)
    public String logout(HttpSession session, HttpServletResponse response) {
        //세션 삭제
        session.invalidate();

        //쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);//setmaxage: 쿠키의 유효시간을 나타내는 메서드로 0으로 설정하면 쿠키를 삭제한다.
        cookie.setPath("/");
        response.addCookie(cookie);

        return "Logout Success";
    }
}
