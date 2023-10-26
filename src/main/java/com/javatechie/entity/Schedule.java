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
    private Date fistDay;

    @Column(name = "last_day")
    private Date lastDay;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @OneToOne
    @JoinColumn(name = "type_id")
    private TypeEgg typeEgg;
}
