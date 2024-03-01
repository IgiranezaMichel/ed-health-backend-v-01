package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Trainer;

import lombok.Getter;
import lombok.Setter;

public class TrainerInput extends Trainer{
    @Getter @Setter
private long trainingId;
}
