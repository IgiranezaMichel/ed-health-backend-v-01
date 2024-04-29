package com.edhealthbackend.model.gql.InputDefs;

import java.time.LocalDateTime;

import com.edhealthbackend.enums.Status;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.SchoolAdmin;

import lombok.Getter;
import lombok.Setter;

public class SchoolAdminInput extends SchoolAdmin{
    public SchoolAdminInput(long id, School school, AccountHolder user, String position, LocalDateTime startingDate,
            LocalDateTime endDate, Status status) {
        super(id, school, user, position, startingDate, endDate, status);
    }
    @Getter @Setter private long schoolId;
    @Getter @Setter private long userId;
}
