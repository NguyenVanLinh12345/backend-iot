package com.javatechie.service.impl;

import com.javatechie.config.UserInfoUserDetails;
import com.javatechie.converter.MachineConverter;
import com.javatechie.converter.ProblemConverter;
import com.javatechie.dto.MachineDto;
import com.javatechie.dto.ProblemDto;
import com.javatechie.entity.Machine;
import com.javatechie.entity.Problem;
import com.javatechie.entity.Schedule;
import com.javatechie.entity.User;
import com.javatechie.repository.MachineRepository;
import com.javatechie.repository.ProblemRepository;
import com.javatechie.repository.ScheduleRepository;
import com.javatechie.repository.UserInfoRepository;
import com.javatechie.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserInfoRepository userInfoRepository;

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

    // 1 api cho ESP 32 update ngay dao trung
    // 1 api cho user thay doi chu ki dao trung
    @Override
    public MachineDto updateMachine(MachineDto machineDto) {
        try {
            User user = new User();
            MachineDto machineResponse = new MachineDto();
            if(machineDto.getEmployeeId() != null) {// TH thay đổi employee quản lý machine
                user = userInfoRepository.findById(machineDto.getEmployeeId()).orElse(null);
            }
            Machine machine = machineRepository.findById(machineDto.getId()).orElse(null);

            if(machine == null) return null;
            machine = MachineConverter.toEntity(machine, machineDto);
            if(machine == null) return new MachineDto();
            Machine machineNew = machineRepository.findByName(machineDto.getName()).orElse(null);
            if(machineNew == null || machineNew.getId().equals(machine.getId())) {
                if(user != null) machine.setUser(user);
                machine = machineRepository.save(machine);
                return MachineConverter.toDto(machine);
            }
            machineResponse.setMessage("Name machine has been duplicated!!");
            return machineResponse;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new MachineDto();
        }
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

    @Override // admin có được phép thêm không?
    public MachineDto saveMachine(MachineDto machine) {
        try {
            Machine machineEntity = MachineConverter.toEntity(machine);
            if(checkNameMachine(machineEntity.getName())) {
                MachineDto machineDto = new MachineDto();
                machineDto.setMessage("Name machine has been duplicated!!");
                return machineDto;
            }
            User userEntity = userInfoRepository.findById(machine.getEmployeeId()).orElse(null);
            if(userEntity == null) {
                return null;
            }
            machineEntity.setUser(userEntity);
            machineEntity = machineRepository.save(machineEntity);
            return MachineConverter.toDto(machineEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MachineDto> findAllByUser(Integer userId) {
        List<Machine> listMachine = machineRepository.findAllByUserId(userId);
        List<MachineDto> listMachineDto = new ArrayList<>();
        for(Machine machine : listMachine) {
            listMachineDto.add(MachineConverter.toDto(machine));
        }
        return listMachineDto;
    }

    @Override
    public List<MachineDto> findAllMachineOfUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserInfoUserDetails userDetails = (UserInfoUserDetails) auth.getPrincipal();
            User user = userInfoRepository.findByEmail(userDetails.getUsername()).orElse(null);
            List<Machine> listMachine = machineRepository.findAllByUserId(user.getId());
            List<MachineDto> listMachineDto = new ArrayList<>();
            for(Machine machine : listMachine) {
                MachineDto machineDto = MachineConverter.toDto(machine);
                List<ProblemDto> listProblem = new ArrayList<>();
                for(Problem problem : machine.getProblemList()) {
                    listProblem.add(ProblemConverter.toDto(problem));
                }
                machineDto.setListProblem(listProblem);
                listMachineDto.add(machineDto);
            }
            return listMachineDto;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean checkNameMachine(String name) {
        return machineRepository.existsByName(name);
    }
}
