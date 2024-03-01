package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Location;

import lombok.Getter;
import lombok.Setter;

public class LocationInput extends Location{
@Getter @Setter
private long locationId;
}
