package com.javatechie.controller;

import com.javatechie.dto.ScheduleDto;
import com.javatechie.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @GetMapping("/schedules")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ScheduleDto> findAll() {
        List<ScheduleDto> scheduleDtos = scheduleService.findAll();
        return scheduleDtos;
    }

    @GetMapping("/schedule")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findOne(@RequestParam("id") Integer id) {
        ScheduleDto scheduleDto = scheduleService.findOne(id);
        if(scheduleDto == null) {
            return ResponseEntity.badRequest().body("Can not found schedule with id = " + id);
        }
        return ResponseEntity.ok(scheduleDto);
    }

    @PostMapping("/schedule/import")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleDto schedule = scheduleService.saveSchedule(scheduleDto, scheduleDto.getMachineId(), scheduleDto.getTypeEggId());
        if(schedule == null) {
            return ResponseEntity.badRequest().body("Save schedule error");
        }
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/schedule")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleDto schedule = scheduleService.updateSchedule(scheduleDto, scheduleDto.getTypeEggId(), scheduleDto.getMachineId());
        if(schedule == null) {
            return ResponseEntity.badRequest().body("Can not update schedule");
        }
        return ResponseEntity.ok(schedule);
    }


    @DeleteMapping("/schedule")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteSchedule(@RequestParam("id") Integer id) {
        String message = scheduleService.deleteSchedule(id);
        if(message.equals("success")) {
            return ResponseEntity.ok("Delete schedule success");
        }
        return ResponseEntity.badRequest().body("Delete schedule fail");
    }
}
