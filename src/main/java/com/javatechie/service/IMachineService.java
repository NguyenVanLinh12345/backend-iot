package com.javatechie.service;

import com.javatechie.dto.MachineDto;

import java.util.List;

public interface IMachineService {

    List<MachineDto> findAll();
    MachineDto findOne(Integer id);
    MachineDto updateMachine(Integer id);
    String deleteMachine(Integer id);
}
