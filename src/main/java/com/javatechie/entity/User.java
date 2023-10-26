package com.javatechie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;
    @Column(name = "username", nullable = false, unique = true)
    private String email;
    @Column(name = "password", length = 8, nullable = false)
    private String password;
    @Column(name = "descriotion", columnDefinition = "text")
    private String description;
    @Column(name = "roles", columnDefinition = "varchar(255)")
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Machine> machineList;
}
