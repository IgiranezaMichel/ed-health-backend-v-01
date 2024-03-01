package com.edhealthbackend.model;
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
public class School{
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
@Lob
private String logo;
@ManyToOne
private Location location;
public School(long id,String name,String logo,Location location){
    this.id=id;
    this.name=name;
    this.logo=logo;
    this.location=location;
}
@OneToMany(mappedBy = "school",targetEntity = Faculty.class,cascade = CascadeType.ALL)
private List<Faculty>facultyList;
@OneToMany(mappedBy = "school",cascade = CascadeType.ALL,targetEntity = Student.class)
private List<Student> studentList;
@OneToMany(mappedBy = "school",cascade = CascadeType.ALL,targetEntity = SchoolAdmin.class)
private List<SchoolAdmin>schoolAdminList;
}
