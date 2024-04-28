package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.TrainingApplication;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.TrainingApplicationPage;
import com.edhealthbackend.services.TrainingApplicationServices;
@Controller
public class TrainingApplicationController {
@Autowired private TrainingApplicationServices trainingApplicationServices;
@MutationMapping
public ResponseEntity<String> changeApplicantStatusByHospitalAdmin(@Argument(name = "applicationId")long applicationId,@Argument(name = "applicationStatus")String applicationStatus){
    return trainingApplicationServices.changeApplicantStatusByHospitalAdmin(applicationId,applicationStatus);
}
@MutationMapping
public ResponseEntity<String>registerStudentTrainingApplication(@Argument(name = "studentId")long studentId,@Argument(name ="trainingId")long trainingId,@Argument(name ="studentApprovalStatus")String approval){
    return trainingApplicationServices.saveStudentTrainingRegistration(studentId,trainingId,approval);
}
@QueryMapping
public TrainingApplicationPage getTrainingApplicantPageByHospitalApprovalStatus(@Argument(name = "status")String status,@Argument(name = "trainingId")long trainingId,@Argument(name ="input")PaginationInput in){
    return trainingApplicationServices.findTrainingApplicantPageByHospitalAdmin(status,trainingId,in);
}

@QueryMapping
public TrainingApplicationPage getStudentTrainingApplicationPage(@Argument(name = "input")PaginationInput in,@Argument(name="studentId")long studentId,@Argument(name = "status")String status){
return trainingApplicationServices.getStudentTrainingApplication(studentId,status,in);
}
@QueryMapping
public List<TrainingApplication>getListOfAllTrainingApplicant(@Argument(name = "trainingId")long trainingId){
    return trainingApplicationServices.getListOfAllTrainingApplicant(trainingId);
}
}
