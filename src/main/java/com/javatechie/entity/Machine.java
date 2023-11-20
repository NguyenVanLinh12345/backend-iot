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
	@Column(name = "name")
	private String name;
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
}
