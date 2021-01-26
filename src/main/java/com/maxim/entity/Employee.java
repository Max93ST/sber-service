package com.maxim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "sur_name", nullable = false)
    private String surName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Of_Birth", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date dateOfBirth;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "dep_id")
    private Long depId;
}
