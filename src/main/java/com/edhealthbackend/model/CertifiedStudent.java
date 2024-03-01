package com.edhealthbackend.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CertifiedStudent {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@ManyToOne
private Student student;
@ManyToOne
private Certificate certificate;
@Enumerated(EnumType.STRING)
private com.edhealthbackend.enums.CertificateStatus CertificateStatus;
private LocalDateTime timeStamp;
@OneToMany(mappedBy = "certifiedStudent")
private List<CertificateValidStatus>listOfCertificationValidation;
}
