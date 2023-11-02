package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "last_egg_turning") // ngày đảo trứng lần cuối
    private Date lastEggTurning;
    @Column(name = "cycle") // chu kì(lưu dưới dạng giờ) - người dùng điều chỉnh đc
    private Double cycle;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "machine")
    private List<Schedule> scheduleList;
    @OneToMany(mappedBy = "machine")
    private List<Problem> problemList;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Schedule> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public List<Problem> getProblemList() {
		return problemList;
	}
	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}
    
    
}
