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
}
