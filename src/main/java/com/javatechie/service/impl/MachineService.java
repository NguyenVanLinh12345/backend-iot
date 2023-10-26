package com.javatechie.service.impl;

import com.javatechie.converter.MachineConverter;
import com.javatechie.dto.MachineDto;
import com.javatechie.entity.Machine;
import com.javatechie.repository.MachineRepository;
import com.javatechie.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService implements IMachineService {
    @Autowired
    private MachineRepository machineRepository;

    // lấy ra toàn bộ machine trong bang machine từ database
    @Override
    public List<MachineDto> findAll() {
        List<Machine> listMachineEntity = machineRepository.findAll();
        List<MachineDto> listMachineDto = new ArrayList<>();
        for(Machine machine : listMachineEntity) {
            MachineDto machineDto = MachineConverter.toDto(machine);
            if(machineDto != null) {
                listMachineDto.add(machineDto);
            }
        }
        return listMachineDto;
    }

    // lấy ra 1 machine từ bảng machine trong database
    @Override
    public MachineDto findOne(Integer id) {
        Machine machine = machineRepository.findById(id).orElse(null);
        if(machine == null) {
            return null;
        }
        MachineDto machineDto = MachineConverter.toDto(machine);
        return machineDto;
    }

    @Override
    public MachineDto updateMachine(Integer id) {
        return null;
    }

    @Override
    public String deleteMachine(Integer id) {
        return null;
    }
}
