package com.edhealthbackend.model;
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

@Entity
@AllArgsConstructor @NoArgsConstructor
public class SchoolAdmin extends Admin{
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter
private long id;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = School.class) @Getter @Setter
private School school;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = AccountHolder.class) @Getter @Setter
private AccountHolder user;
}
