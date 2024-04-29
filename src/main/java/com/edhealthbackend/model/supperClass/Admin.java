package com.edhealthbackend.model.supperClass;

import java.time.LocalDateTime;
import com.edhealthbackend.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
private String position;
private LocalDateTime startingDate;
private LocalDateTime endDate;
@Enumerated(EnumType.STRING)
private Status status;
}
