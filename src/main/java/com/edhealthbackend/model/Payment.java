package com.edhealthbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.*;
@AllArgsConstructor @NoArgsConstructor @Data @Entity
public class Payment {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String paymentCode;
private int amount;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = CertificateValidStatus.class)
private CertificateValidStatus certificateValidStatus;
private LocalDateTime timeStamp;
}
