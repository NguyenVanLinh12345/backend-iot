package com.javatechie.controller;

import com.javatechie.dto.ScheduleDto;
import com.javatechie.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    public List<ScheduleDto> findAll(@RequestParam("machineId") Integer machineId) {
        List<ScheduleDto> scheduleDtos = scheduleService.findAll(machineId);
        return scheduleDtos;
    }

    @GetMapping("/schedule")
    public ResponseEntity<?> findOne(@RequestParam("id") Integer id) {
        ScheduleDto scheduleDto = scheduleService.findOne(id);
        if(scheduleDto == null) {
            return ResponseEntity.badRequest().body("Can not found schedule with id = " + id);
        }
        return ResponseEntity.ok(scheduleDto);
    }

    @PostMapping("/schedule/import")
    public ResponseEntity<?> saveSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleDto schedule = scheduleService.saveSchedule(scheduleDto, scheduleDto.getMachineId(), scheduleDto.getTypeEggId());
        if(schedule == null) {
            return ResponseEntity.badRequest().body("Save schedule error");
        }
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/schedule")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleDto schedule = scheduleService.updateSchedule(scheduleDto, scheduleDto.getTypeEggId(), scheduleDto.getMachineId());
        if(schedule == null) {
            return ResponseEntity.badRequest().body("Can not update schedule");
        }
        return ResponseEntity.ok(schedule);
    }


    @DeleteMapping("/schedule")
    public ResponseEntity<?> deleteSchedule(@RequestParam("id") Integer id) {
        String message = scheduleService.deleteSchedule(id);
        if(message.equals("success")) {
            return ResponseEntity.ok("Delete schedule success");
        }
        return ResponseEntity.badRequest().body("Delete schedule fail");
    }
}
