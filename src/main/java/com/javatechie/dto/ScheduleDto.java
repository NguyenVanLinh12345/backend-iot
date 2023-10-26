package com.javatechie.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDto {
    private Integer id;
    private Date fistDay;
    private Date lastDay;
    private Integer quantity;
    private String description;
}
