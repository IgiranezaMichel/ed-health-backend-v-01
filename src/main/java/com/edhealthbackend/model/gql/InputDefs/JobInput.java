package com.edhealthbackend.model.gql.InputDefs;
import com.edhealthbackend.model.Job;

import lombok.Getter;
import lombok.Setter;
public class JobInput extends Job{
    @Getter @Setter
    private long hospitalId;
}
