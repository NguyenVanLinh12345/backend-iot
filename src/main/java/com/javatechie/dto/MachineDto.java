package com.javatechie.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class MachineDto {

    private Integer id;
    private Date lastEggTurning;
    private Double cycle;
	private String name;
    private String message;
}
