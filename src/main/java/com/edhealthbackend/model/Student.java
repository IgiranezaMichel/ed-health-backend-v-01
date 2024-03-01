package com.edhealthbackend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.edhealthbackend.enums.StudentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
@Id @GeneratedValue(strategy =GenerationType.AUTO)
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = School.class)
private School school;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class)
private AccountHolder user;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Department.class)
private Department department;
private LocalDateTime startingDate;
private LocalDateTime endingDate;
@Enumerated(EnumType.STRING)
private StudentStatus status;
@OneToMany(mappedBy = "student",cascade = CascadeType.ALL,targetEntity=TrainingApplication.class)
private List<TrainingApplication>TrainingApplicationList;
@OneToMany(mappedBy = "student",cascade = CascadeType.ALL,targetEntity = CertifiedStudent.class)
private List<CertifiedStudent>certificateList;
}
