package com.edhealthbackend.model;
import java.time.LocalDateTime;

import com.edhealthbackend.enums.Status;
import com.edhealthbackend.model.supperClass.Admin;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity @AllArgsConstructor @NoArgsConstructor
public class HospitalAdmin extends Admin{
@Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Hospital.class) @Getter @Setter
private Hospital hospital;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class) @Getter @Setter
private AccountHolder user;
public HospitalAdmin(long id,Hospital hospital,AccountHolder user,String position,LocalDateTime startingDate,LocalDateTime endDate,Status status){
this.setEndDate(endDate);
this.setHospital(hospital);
this.setId(id);
this.setPosition(position);
this.setStartingDate(startingDate);
this.setStatus(status);
this.setUser(user);
}
}
