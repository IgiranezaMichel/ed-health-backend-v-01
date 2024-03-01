package com.edhealthbackend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Lob
    private String logo;
    private String description;
    private LocalDateTime timeStamp;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity =Location.class)
    private Location location;
    public Hospital(long id,String name,String logo,String description,LocalDateTime timeStamp,Location location){
     this.id=id;
     this.name=name;
     this.logo=logo;
     this.description=description;
     this.timeStamp=timeStamp;
     this.location=location;
    }
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL,targetEntity =Training.class)
    private List<Training>hospitalTrainingList;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL,targetEntity =Job.class)
    private List<Job>jobList;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL,targetEntity = HospitalAdmin.class)
    private List<HospitalAdmin> hospitalAdminList;
}
