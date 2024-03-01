package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.gql.InputDefs.AnnualAcademicResultInput;
import com.edhealthbackend.services.AnnualAcademicReportServices;

@Controller
public class AnnualAcademicResultController {
@Autowired private AnnualAcademicReportServices academicReportServices;
@MutationMapping
public String saveAnnualAcademicReport(@Argument(name = "input") AnnualAcademicResultInput in){
    return academicReportServices.saveAcademicReport(in);
}
}
