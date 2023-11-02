package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
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
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
    
    
}
