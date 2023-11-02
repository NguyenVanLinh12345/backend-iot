package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "type_egg")
public class TypeEgg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
	@Column(name = "temperature")
	private Double temperature;
	@Column(name = "number_hatch")
	private Integer numberHatch;
	@Column(name = "number_turn")
	private Integer numberTurn;
	@Column(name = "humidity")
	private Double humidity;
    @OneToMany(mappedBy = "typeEgg")
    private List<Schedule> schedule;
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

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
}
