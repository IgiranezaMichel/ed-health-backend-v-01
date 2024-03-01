package com.edhealthbackend.model;

import com.edhealthbackend.model.supperClass.Requirement;

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
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequirement extends Requirement{
@Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Training.class) @Getter @Setter
private Training training;
public TrainingRequirement(long id,Training training,String description){
this(id, training);
this.setDescription(description);
}
}
