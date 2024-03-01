package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.JobRequirement;

import lombok.Getter;
import lombok.Setter;
public class JobRequirementInput extends JobRequirement{
    @Getter @Setter
private long jobId;
}
