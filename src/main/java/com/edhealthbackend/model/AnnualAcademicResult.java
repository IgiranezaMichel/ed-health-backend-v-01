package com.edhealthbackend.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnualAcademicResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double totalMarks;
    private double disciplineMarks;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;
    private LocalDateTime timeStamp;
}
