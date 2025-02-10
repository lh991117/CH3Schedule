package com.example.schedule.Controller;

import com.example.schedule.Dto.CreateScheduleRequestDto;
import com.example.schedule.Dto.ScheduleResponseDto;
import com.example.schedule.Dto.ScheduleWithUsernameResponseDto;
import com.example.schedule.Dto.UpdateScheduleRequestDto;
import com.example.schedule.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    //일정을 저장
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto){
        ScheduleResponseDto scheduleResponseDto=
                scheduleService.save(
                        requestDto.getTodoTitle(),
                        requestDto.getTodoContent(),
                        requestDto.getUsername()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //저장된 일정을 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    //저장된 일정을 id를 통해서 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithUsernameResponseDto> findById(@PathVariable Long id){
        ScheduleWithUsernameResponseDto SWUResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(SWUResponseDto, HttpStatus.OK);
    }
    
    //ID를 통해서 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto){
        scheduleService.updateSchedule(id, requestDto.getTodoTitle(), requestDto.getTodoContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ID를 통해서 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> delete(@PathVariable Long id){
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
