package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Department;

import lombok.Getter;
import lombok.Setter;

public class DepartmentInput extends Department{
@Getter @Setter private long facultyId;
}
