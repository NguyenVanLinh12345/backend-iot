package com.javatechie.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProblemDto {
    private Integer id;
    private String description;
    private String type;
	private Integer machineId;
	private Date createDate;
	private Date modiledDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}
