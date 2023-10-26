package com.javatechie.converter;

import com.javatechie.dto.ScheduleDto;
import com.javatechie.entity.Schedule;
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
}
