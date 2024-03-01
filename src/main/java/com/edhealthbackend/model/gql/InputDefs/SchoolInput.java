package com.edhealthbackend.model.gql.InputDefs;
import com.edhealthbackend.model.School;

import lombok.Getter;
import lombok.Setter;

public class SchoolInput extends School{
    @Getter @Setter private long locationId;
}
