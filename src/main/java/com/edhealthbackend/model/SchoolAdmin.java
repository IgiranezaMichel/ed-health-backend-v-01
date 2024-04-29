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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class SchoolAdmin extends Admin{
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = School.class) @Getter @Setter
private School school;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class) @Getter @Setter
private AccountHolder user;
public SchoolAdmin(long id, School school, AccountHolder user,String position, LocalDateTime startingDate, LocalDateTime endDate, Status status) {
    super(position, startingDate, endDate, status);
    this.id = id;
    this.school = school;
    this.user = user;
}
}
