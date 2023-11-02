package com.javatechie.service;

import com.javatechie.dto.MachineDto;

import java.util.List;

public interface IMachineService {

    List<MachineDto> findAll();
    MachineDto findOne(Integer id);
    MachineDto updateMachine(MachineDto machineDto);
    String deleteMachine(Integer id);
    MachineDto saveMachine(MachineDto machine);
}
