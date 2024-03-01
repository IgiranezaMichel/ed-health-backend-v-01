package com.edhealthbackend.model;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private LocalDateTime timeStamp= LocalDateTime.now();
    private LocalDateTime deadline;
    private String ncnmApprovalStatus;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity=Location.class)
    private Location location;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Hospital.class)
    private Hospital hospital;
    public Training(long id,String title,String description,LocalDateTime deadline,String ncnmApprovalStatus,Location location,Hospital hospital){
        this.title=title;
        this.id=id;
        this.description=description;
        this.deadline=deadline;
        this.ncnmApprovalStatus=ncnmApprovalStatus;
        this.location=location;
        this.hospital=hospital;

    }
    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL,targetEntity = Trainer.class)
    private List<Trainer> trainers;
    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL,targetEntity =TrainingApplication.class)
    private List<TrainingApplication>applicantList;
    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL,targetEntity = Certificate.class)
    private List<Certificate>certificatedPersonList;
    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL,targetEntity = TrainingRequirement.class)
    private List<TrainingRequirement>trainingRequirementList;
}
