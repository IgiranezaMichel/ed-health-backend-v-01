package com.edhealthbackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class JobApplication {
@Id @GeneratedValue(strategy=GenerationType.AUTO)
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Job.class)
private Job job;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Student.class)
private Student student;
private LocalDateTime timeStamp;
private String status;
}
