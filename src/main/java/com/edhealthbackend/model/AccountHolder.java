package com.edhealthbackend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.edhealthbackend.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor @NoArgsConstructor @Entity
public class AccountHolder {
@Id()
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
private String name;
private String gender;
@Column(unique = true,updatable=true)
private String email;
private String phoneNumber;
@Lob
private String profilePicture;
private LocalDateTime dob;
private String password;
@Enumerated(EnumType.STRING)
private Role role;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,targetEntity =Student.class )
private List<Student>studentList;
@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,targetEntity = SchoolAdmin.class)
private List<SchoolAdmin>schoolAdminList;
@OneToMany(mappedBy= "user",cascade = CascadeType.ALL,targetEntity = HospitalAdmin.class)
private List<HospitalAdmin>hospitalAdminList;
}
