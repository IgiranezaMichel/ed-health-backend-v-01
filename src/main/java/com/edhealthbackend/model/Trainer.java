package com.edhealthbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trainer {
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
private long id;
private String trainerTitle;
private String name;
private String phoneNumber;
@Lob
private String profilePicture;
@Lob
private String signature;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Training.class)
private Training training;
}
