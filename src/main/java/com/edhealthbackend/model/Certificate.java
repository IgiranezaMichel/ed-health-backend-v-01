package com.edhealthbackend.model;

import java.time.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String signature;
    private String stamp;
    private LocalDateTime timeStamp;
    @ManyToOne
    private Training training;

    public Certificate(long id, String title, String description, String signature, String stamp,
            LocalDateTime now, Training training) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.signature = signature;
        this.stamp = stamp;
        this.timeStamp = now;
        this.training = training;
    }
    @OneToMany(mappedBy = "certificate")
    private List<CertifiedStudent> certifiedStudentList;
}
