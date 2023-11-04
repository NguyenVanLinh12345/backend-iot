package com.javatechie.service.impl;

import com.javatechie.converter.ScheduleConverter;
import com.javatechie.dto.ScheduleDto;
import com.javatechie.entity.Machine;
import com.javatechie.entity.Schedule;
import com.javatechie.entity.TypeEgg;
import com.javatechie.repository.MachineRepository;
import com.javatechie.repository.ScheduleRepository;
import com.javatechie.repository.TypeEggRepository;
import com.javatechie.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private TypeEggRepository typeEggRepository;

    @Override
    public List<ScheduleDto> findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for(Schedule schedule : scheduleList) {
            scheduleDtos.add(ScheduleConverter.toDto(schedule));
        }
        return scheduleDtos;
    }

    @Override
    public ScheduleDto findOne(Integer id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        return ScheduleConverter.toDto(schedule);
    }

    @Override
    public ScheduleDto saveSchedule(ScheduleDto scheduleDto, Integer machineId, Integer typeEggId) {
        try {
            Machine machine = machineRepository.findById(machineId).orElse(null);
            TypeEgg typeEgg = typeEggRepository.findById(typeEggId).orElse(null);
            Schedule schedule = ScheduleConverter.toEntity(scheduleDto);
            if(machine == null || typeEgg == null || schedule == null) {
                return null;
            }
            schedule.setMachine(machine);
            schedule.setTypeEgg(typeEgg);
            schedule = scheduleRepository.save(schedule);
            return ScheduleConverter.toDto(schedule);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ScheduleDto updateSchedule(ScheduleDto scheduleDto, Integer typeEggId, Integer machineId) {
        try {
            Machine machine = machineRepository.findById(machineId).orElse(null);
            TypeEgg typeEgg = typeEggRepository.findById(typeEggId).orElse(null);
            Schedule schedule = scheduleRepository.findById(scheduleDto.getId()).orElse(null);
            if(schedule == null) {
                return null;
            }
            schedule = ScheduleConverter.toEntity(schedule, scheduleDto, machine, typeEgg);
            if(schedule == null) {
                return null;
            }
            schedule = scheduleRepository.save(schedule);
            return ScheduleConverter.toDto(schedule);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ScheduleDto();
        }
    }

    @Override
    public String deleteSchedule(Integer id) {
        try {
            Schedule schedule = scheduleRepository.findById(id).orElse(null);
            if(schedule == null) {
                return "Can not found schedule with id = " + id;
            }
            scheduleRepository.delete(schedule);
            return "success";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}
