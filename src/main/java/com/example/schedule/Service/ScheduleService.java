package com.example.schedule.Service;

import com.example.schedule.Dto.ScheduleResponseDto;
import com.example.schedule.Entity.Schedule;
import com.example.schedule.Entity.User;
import com.example.schedule.Repository.ScheduleRepository;
import com.example.schedule.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //일정을 저장하는 메서드
    public ScheduleResponseDto save(String todoTitle, String todoContent, String username){
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(todoTitle, todoContent);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTodoTitle(), schedule.getTodoContent());
    }

    //저장된 일정을 조회하는 메서드
    public List<ScheduleResponseDto> findAll(){
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }
}
