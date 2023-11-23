package com.javatechie.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDto {
    private Integer id;
    private Date fistDay;
    private Date lastDay;
    private Integer quantity;
    private String description;
	private Integer machineId;
	private Integer typeEggId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFistDay() {
		return fistDay;
	}
	public void setFistDay(Date fistDay) {
		this.fistDay = fistDay;
	}
	public Date getLastDay() {
		return lastDay;
	}
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
