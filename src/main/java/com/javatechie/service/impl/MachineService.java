package com.javatechie.service.impl;

import com.javatechie.converter.MachineConverter;
import com.javatechie.dto.MachineDto;
import com.javatechie.entity.Machine;
import com.javatechie.entity.Problem;
import com.javatechie.entity.Schedule;
import com.javatechie.repository.MachineRepository;
import com.javatechie.repository.ProblemRepository;
import com.javatechie.repository.ScheduleRepository;
import com.javatechie.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService implements IMachineService {
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

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
        try {
            Machine machine = machineRepository.findById(id).orElse(null);
            List<Problem> problemList = machine.getProblemList();
            // remove problem of machine
            for(Problem problem : problemList) {
                problem.setMachine(null);
                problemRepository.save(problem);
            }
            List<Schedule> scheduleList = machine.getScheduleList();
            // remove schedule of machine
            for(Schedule schedule : scheduleList) {
                schedule.setMachine(null);
                scheduleRepository.save(schedule);
            }
            machineRepository.delete(machine);
            return "Delete machine with id = " + id + " success";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Can not delete machine with id = " + id;
        }
    }

    @Override
    public MachineDto saveMachine(MachineDto machine) {
        try {
            Machine machineEntity = MachineConverter.toEntity(machine);

            machineEntity = machineRepository.save(machineEntity);
            return MachineConverter.toDto(machineEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
