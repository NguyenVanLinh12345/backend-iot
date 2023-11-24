package com.javatechie.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class MachineDto {

    private Integer id;
    private Date lastEggTurning;
    private Double cycle;
	private String name;
    private String message;
    private Integer employeeId;
    private String employeeName;
    private List<ProblemDto> listProblem;
}
