package com.edhealthbackend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CertificateValidStatus {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private LocalDateTime timeStamp;
private LocalDateTime deadline;
@OneToMany(mappedBy = "certificateValidStatus")
private List<Payment> paymentList;
@ManyToOne
private CertifiedStudent certifiedStudent;
}
