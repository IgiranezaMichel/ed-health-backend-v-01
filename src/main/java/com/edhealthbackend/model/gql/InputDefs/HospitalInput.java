package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.Hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
public class HospitalInput extends Hospital{
@Getter @Setter
private long locationId;
@Getter @Setter
private long hospitalId;
}
