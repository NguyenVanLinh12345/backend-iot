package com.javatechie.dto;

import lombok.Data;

@Data
public class TypeEggDto {
    private Integer id;
    private String name;
    private String description;
	private Double temperature;
	private Integer numberHatch;
	private Integer numberTurn;
	private Double humidity;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Integer getNumberHatch() {
		return numberHatch;
	}

	public void setNumberHatch(Integer numberHatch) {
		this.numberHatch = numberHatch;
	}

	public Integer getNumberTurn() {
		return numberTurn;
	}

	public void setNumberTurn(Integer numberTurn) {
		this.numberTurn = numberTurn;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
}
