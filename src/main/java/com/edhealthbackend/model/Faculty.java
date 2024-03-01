package com.edhealthbackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Faculty {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
@ManyToOne()
private School school;
public Faculty(long id,String name,School school){
    this.setId(id);
    this.setName(name);
    this.setSchool(school);
}
@OneToMany(mappedBy = "faculty")
private List<Department>departmentList;
}
