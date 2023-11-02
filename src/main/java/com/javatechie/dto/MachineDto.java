package com.javatechie.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class MachineDto {

    private Integer id;
    private Date lastEggTurning;
    private Double cycle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLastEggTurning() {
		return lastEggTurning;
	}
	public void setLastEggTurning(Date lastEggTurning) {
		this.lastEggTurning = lastEggTurning;
	}
	public Double getCycle() {
		return cycle;
	}
	public void setCycle(Double cycle) {
		this.cycle = cycle;
	}
    
    
}
