package com.edhealthbackend.model.supperClass;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountHolderInformation {
private String name;
private String gender;
private String email;
private String profilePicture;
private String phoneNumber;
private LocalDateTime dob;
private String role;
private String password;
}   
