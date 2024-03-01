package com.edhealthbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TrainingApplication {
@Id @GeneratedValue(strategy =GenerationType.AUTO)
private long id;
private String hospitalApprovalStatus;
private LocalDateTime hospitalApprovalTimeStamp;
private String studentApprovalStatus;
private LocalDateTime studentApprovalTimeStamp;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Training.class)
private Training training;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Student.class)
private Student student;
}
