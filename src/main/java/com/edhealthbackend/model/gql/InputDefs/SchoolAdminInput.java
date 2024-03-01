package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.SchoolAdmin;

import lombok.Getter;
import lombok.Setter;

public class SchoolAdminInput extends SchoolAdmin{
    @Getter @Setter private long schoolId;
    @Getter @Setter private long userId;
}
