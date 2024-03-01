package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.gql.InputDefs.JobRequirementInput;
import com.edhealthbackend.services.JobRequirementServices;

@Controller
public class JobRequirementController {
@Autowired private JobRequirementServices jobRequirementServices;
@MutationMapping
public String saveJobRequirement(@Argument(name="input")JobRequirementInput in){
 return jobRequirementServices.saveJobRequirement(in);
}
}
