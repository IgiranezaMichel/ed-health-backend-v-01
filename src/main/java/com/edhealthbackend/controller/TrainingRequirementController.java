package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.TrainingRequirement;
import com.edhealthbackend.model.gql.InputDefs.TrainingRequirementInput;
import com.edhealthbackend.services.TrainingRequirementServices;
import com.edhealthbackend.services.TrainingServices;
@Controller
public class TrainingRequirementController {
@Autowired private TrainingRequirementServices tRequirementServices;
@MutationMapping
public String saveTrainingRequirementList(@Argument("input")List<TrainingRequirement> input){
    tRequirementServices.saveListOfRequirement(input);
    return "Requirement saved successful";
}
@Autowired private TrainingServices trainingServices;
@MutationMapping
public String saveTrainingRequirement(@Argument(name = "input")TrainingRequirementInput data){
    Training training=trainingServices.findTrainingById(data.getTrainingId());
     tRequirementServices.saveOrUpdate(new TrainingRequirement(data.getId(), training,data.getDescription()));
    return "Training requirement saved successful";
}
@MutationMapping
public String deleteTrainingRequirement(long id){
    return tRequirementServices.deleteById(id);
}
}
