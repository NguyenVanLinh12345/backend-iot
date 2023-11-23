package com.javatechie.converter;

import com.javatechie.dto.ProblemDto;
import com.javatechie.entity.Problem;
import org.modelmapper.ModelMapper;

public class ProblemConverter {

    public static ProblemDto toDto(Problem problem) {
        try {
            ModelMapper mapper = new ModelMapper();
            ProblemDto problemDto = mapper.map(problem, ProblemDto.class);
            return problemDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Problem toEntity(ProblemDto problemDto) {
        try {
            ModelMapper mapper = new ModelMapper();
            Problem problem = mapper.map(problemDto, Problem.class);
            return problem;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
