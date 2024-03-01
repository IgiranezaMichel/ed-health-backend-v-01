package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.AnnualAcademicResult;

import lombok.Getter;
import lombok.Setter;

public class AnnualAcademicResultInput extends AnnualAcademicResult{
   @Getter @Setter private long studentId;
}
