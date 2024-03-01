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
public class JobRequirement extends Requirement{
@Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter
private long id;
@Getter @Setter 
@ManyToOne(cascade =CascadeType.ALL,targetEntity=Job.class)
private Job job;
public JobRequirement(long id,Job job,String description){
    this.setDescription(description);
    this.setId(id);
    this.setJob(job); 
}
}
