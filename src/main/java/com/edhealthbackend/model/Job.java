package com.edhealthbackend.model;
import java.time.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Job {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String title;
private String description;
private LocalDateTime deadline;
private LocalDateTime timeStamp;
private int numberOfEmployee;
@Lob
private String picture;
private String status;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Hospital.class)
private Hospital hospital;
public Job(long id, String title,String description,LocalDateTime deadline,Hospital hospital,String picture,String status,int numberOfEmployee){
    this.id=id;
    this.title=title;
    this.description=description;
    this.deadline=deadline;
    this.hospital=hospital;
    this.picture=picture;
    this.status=status;
    this.numberOfEmployee=numberOfEmployee;
    this.timeStamp=LocalDateTime.now();
}
@Column(columnDefinition = "text")
private String jobRequirement;
@OneToMany(mappedBy="job",cascade = CascadeType.ALL,targetEntity = JobApplication.class)
private List<JobApplication>jobApplicantList;
}
