package com.example.schedule.Service;

import com.example.schedule.Dto.UserResponseDto;
import com.example.schedule.Entity.User;
import com.example.schedule.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto signUp(String username, String password, String email) {
        User user=new User(username, email, password);
        User savedUser=userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }
}
