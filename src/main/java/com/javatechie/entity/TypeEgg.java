package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne(mappedBy = "typeEgg")
    private Schedule schedule;
}
