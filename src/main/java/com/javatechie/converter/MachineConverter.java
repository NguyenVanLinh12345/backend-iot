package com.javatechie.converter;

import com.javatechie.dto.MachineDto;
import com.javatechie.entity.Machine;
import org.modelmapper.ModelMapper;

public class MachineConverter {

    public static MachineDto toDto(Machine machine) {
        try {
            ModelMapper mapper = new ModelMapper();
            MachineDto machineDto = mapper.map(machine, MachineDto.class);
            return machineDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Machine toEntity(Machine machine, MachineDto machineDto) {
        try {
            if(machineDto.getCycle() != null) {
                machine.setCycle(machineDto.getCycle());
            }
            if(machineDto.getLastEggTurning() != null) {
                machine.setLastEggTurning(machineDto.getLastEggTurning());
            }
            return machine;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
