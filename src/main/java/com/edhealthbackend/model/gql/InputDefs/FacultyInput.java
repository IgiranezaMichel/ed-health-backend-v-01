package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Faculty;
import lombok.Getter;
import lombok.Setter;

public class FacultyInput extends Faculty{
@Getter @Setter
private long schoolId;
public FacultyInput(long id,String name,long schoolId){
    this.setId(id);
    this.setName(name);
    this.setSchoolId(schoolId);
}
}
