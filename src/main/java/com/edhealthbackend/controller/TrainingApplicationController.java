package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.TrainingApplicationPage;
import com.edhealthbackend.services.TrainingApplicationServices;
@Controller
public class TrainingApplicationController {
@Autowired private TrainingApplicationServices trainingApplicationServices;
@MutationMapping
public ResponseEntity<String> changeApplicantStatusByHospitalAdmin(@Argument(name = "trainingId")long trainingId,@Argument(name = "trainingStatus")String trainingStatus){
    return trainingApplicationServices.changeApplicantStatusByHospitalAdmin(trainingId,trainingStatus);
}
@QueryMapping
public TrainingApplicationPage getTrainingApplicantPageByHospitalApprovalStatus(@Argument(name = "status")String status,@Argument(name = "trainingId")long trainingId,@Argument(name ="input")PaginationInput in){
    return trainingApplicationServices.findTrainingApplicantPageByHospitalAdmin(status,trainingId,in);
}
@MutationMapping
public ResponseEntity<String>registerStudentTrainingApplication(@Argument(name = "studentId")long studentId,@Argument(name ="trainingId")long trainingId,@Argument(name ="studentApprovalStatus")String approval){
    return trainingApplicationServices.saveStudentTrainingRegistration(studentId,trainingId,approval);
}
}
