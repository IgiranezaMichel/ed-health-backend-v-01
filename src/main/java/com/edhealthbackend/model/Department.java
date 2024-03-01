package com.edhealthbackend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Department {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
private int totalCourse;
private int totalCredit;
@ManyToOne
private Faculty faculty;
@OneToMany(mappedBy = "department")
private List<Student>studentList;
}
