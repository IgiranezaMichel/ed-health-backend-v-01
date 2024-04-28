package com.edhealthbackend.model;

import java.time.*;

import jakarta.persistence.CascadeType;
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
@Data
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @Lob
    private String userSignature;
    @Lob
    private String hospitalStamp;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class)
    private AccountHolder accountHolder;
    private LocalDateTime timeStamp;
    @ManyToOne
    private Training training;
    private boolean offeredHasToPay;
    private double price;
    public Certificate(long id, String title, String description, String signature, String stamp,
            LocalDateTime now, Training training,AccountHolder accountHolder,boolean offeredHasToPay,double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userSignature = signature;
        this.hospitalStamp = stamp;
        this.timeStamp = now;
        this.training = training;
        this.accountHolder=accountHolder;
        this.offeredHasToPay=offeredHasToPay;
        this.price=price;
    }
    @OneToMany(mappedBy = "certificate")
    private List<CertifiedStudent> certifiedStudentList;
}
