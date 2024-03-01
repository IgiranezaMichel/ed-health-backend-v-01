package com.edhealthbackend.model.gql.InputDefs;

import java.time.LocalDateTime;

import com.edhealthbackend.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data 
public class AdminInput {
private long id;
private String position;
private LocalDateTime startingDate;
private LocalDateTime endDate;
@Enumerated(EnumType.STRING)
private Status status;
}
