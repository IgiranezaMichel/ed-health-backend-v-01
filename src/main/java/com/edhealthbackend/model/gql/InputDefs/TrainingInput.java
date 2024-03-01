package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Training;

import lombok.Getter;
import lombok.Setter;

public class TrainingInput extends Training{
 @Getter @Setter
private long hospitalId;
@Getter @Setter
private long locationId;
}
