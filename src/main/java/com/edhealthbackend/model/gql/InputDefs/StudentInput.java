package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Student;

import lombok.Getter;
import lombok.Setter;

public class StudentInput extends Student{
@Getter @Setter
private long userId;
@Getter @Setter
private long schoolId;
@Getter @Setter
private long departmentId;
}
