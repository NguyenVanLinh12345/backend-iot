package com.javatechie.service;

import com.javatechie.dto.ProblemDto;

import java.util.List;

public interface IProblemService {
    ProblemDto saveProblem(ProblemDto problemDto);
    List<ProblemDto> findAllByMachine(Integer machineId);
    ProblemDto findOneProblem(Integer id);
    String deleteProblem(Integer id);

}
