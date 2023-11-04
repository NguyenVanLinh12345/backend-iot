package com.javatechie.converter;

import com.javatechie.dto.ScheduleDto;
import com.javatechie.entity.Machine;
import com.javatechie.entity.Schedule;
import com.javatechie.entity.TypeEgg;
import org.modelmapper.ModelMapper;

public class ScheduleConverter {

    public static ScheduleDto toDto(Schedule schedule) {
        try {
            ModelMapper mapper = new ModelMapper();
            ScheduleDto scheduleDto = mapper.map(schedule, ScheduleDto.class);
            return scheduleDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Schedule toEntity(ScheduleDto scheduleDto) {
        try {
            ModelMapper mapper = new ModelMapper();
            Schedule schedule = mapper.map(scheduleDto, Schedule.class);
            return schedule;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Schedule toEntity(Schedule schedule, ScheduleDto scheduleDto, Machine machine, TypeEgg typeEgg) {
        try {
            if(scheduleDto.getFistDay() != null) {
                schedule.setFistDay(scheduleDto.getFistDay());
            }
            if(scheduleDto.getLastDay() != null) {
                schedule.setLastDay(scheduleDto.getLastDay());
            }
            if(scheduleDto.getDescription() != null) {
                schedule.setDescription(scheduleDto.getDescription());
            }
            if(scheduleDto.getQuantity() != null) {
                schedule.setQuantity(scheduleDto.getQuantity());
            }
            if(machine != null) {
                schedule.setMachine(machine);
            }
            if(typeEgg != null) {
                schedule.setTypeEgg(typeEgg);
            }
            return schedule;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
