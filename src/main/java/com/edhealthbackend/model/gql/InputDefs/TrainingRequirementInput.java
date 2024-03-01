package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.TrainingRequirement;

import lombok.Getter;
import lombok.Setter;

public class TrainingRequirementInput extends TrainingRequirement{
@Getter @Setter
private long trainingId;
}
