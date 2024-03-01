package com.edhealthbackend.model.supperClass;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Requirement {
private String description;
}
