package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fist_day")
    private Date fistDay; //
    @Column(name = "last_day")
    private Date lastDay; //
    @Column(name = "quantity")
    private Integer quantity; //
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEgg typeEgg; //
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
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	public TypeEgg getTypeEgg() {
		return typeEgg;
	}
	public void setTypeEgg(TypeEgg typeEgg) {
		this.typeEgg = typeEgg;
	}
    
    
}
