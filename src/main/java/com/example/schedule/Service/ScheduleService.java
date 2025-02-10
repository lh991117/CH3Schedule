package com.example.schedule.Service;

import com.example.schedule.Dto.ScheduleResponseDto;
import com.example.schedule.Dto.ScheduleWithUsernameResponseDto;
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

    //저장된 일정들을 조회하는 메서드
    public List<ScheduleResponseDto> findAll(){
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //저장된 일정 중 조회하고 싶은 일정을 조회하는 메서드
    public ScheduleWithUsernameResponseDto findById(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();

        return new ScheduleWithUsernameResponseDto(findSchedule.getTodoTitle(), findSchedule.getTodoContent(), writer.getUsername());
    }

    //ID를 통해서 일정 수정
    public void updateSchedule(Long id, String todoTitle, String todoContent){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateSchedule(todoTitle, todoContent);
    }

    //ID를 통한 일정 삭제
    public void deleteSchedule(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }
}
