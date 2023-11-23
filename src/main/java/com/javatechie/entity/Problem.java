package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

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
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "modifiled_date")
	private Date modiledDate;
    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;
    
}
