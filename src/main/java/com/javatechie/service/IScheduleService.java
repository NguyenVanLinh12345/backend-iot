package com.javatechie.service;

import com.javatechie.dto.ScheduleDto;

import java.util.List;

public interface IScheduleService {
    List<ScheduleDto> findAll(Integer machineId);
    ScheduleDto findOne(Integer id);
    ScheduleDto saveSchedule(ScheduleDto scheduleDto, Integer machineId, Integer typeEggId);
    ScheduleDto updateSchedule(ScheduleDto scheduleDto, Integer typeEggId, Integer machineId);
    String deleteSchedule(Integer id);
}
