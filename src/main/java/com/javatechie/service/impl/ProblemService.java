package com.javatechie.service.impl;

import com.javatechie.converter.ProblemConverter;
import com.javatechie.dto.ProblemDto;
import com.javatechie.entity.Machine;
import com.javatechie.entity.Problem;
import com.javatechie.repository.MachineRepository;
import com.javatechie.repository.ProblemRepository;
import com.javatechie.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemService implements IProblemService {

    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public ProblemDto saveProblem(ProblemDto problemDto) {
        Problem problem = ProblemConverter.toEntity(problemDto);
        Machine machine = machineRepository.findById(problemDto.getMachineId()).orElse(null);
        if(problem == null || machine == null) {
            return null;
        }
        problem.setCreateDate(new Date(System.currentTimeMillis()));
        problem.setMachine(machine);
        problem = problemRepository.save(problem);
        return ProblemConverter.toDto(problem);
    }

    @Override
    public List<ProblemDto> findAllByMachine(Integer machineId) {
        List<Problem> problemList = problemRepository.findAllByMachineId(machineId);
        List<ProblemDto> problemDtoList = new ArrayList<>();
        for(Problem problem : problemList ) {
            ProblemDto problemDto = ProblemConverter.toDto(problem);
            if(problemDto != null) {
                problemDtoList.add(problemDto);
            }
        }
        return problemDtoList;
    }

    @Override
    public ProblemDto findOneProblem(Integer id) {
        Problem problem = problemRepository.findById(id).orElse(null);
        if(problem == null) {
            return null;
        }
        return ProblemConverter.toDto(problem);
    }

    @Override
    public String deleteProblem(Integer id) {
        try {
            problemRepository.deleteById(id);
            return "success";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
