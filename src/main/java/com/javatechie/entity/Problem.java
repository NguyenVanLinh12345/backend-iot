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
}
