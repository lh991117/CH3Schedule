package com.example.schedule.Repository;

import com.example.schedule.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID에는 정보가 존재하지 않습니다."));
    }

    Optional<User> findUserByUsername(String username);

    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저이름은 존재하지 않습니다."));
    }

    Optional<User> findUserByEmailAndPassword(String email, String password);

    default Long findIDByEmailAndPassword(String email, String password){
        return findUserByEmailAndPassword(email, password).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 email과 password는 존재하지 않습니다.")).getId();
    }

    Optional<User> findUserByEmail(String email);

    default User findUserByEmailOrElseThrow(String email) {
        return findUserByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 이메일이 존재하지 않습니다."));
    }
}
