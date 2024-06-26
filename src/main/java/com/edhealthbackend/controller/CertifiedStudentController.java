package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.gql.InputDefs.CertifiedStudentInput;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.CertifiedStudentPage;
import com.edhealthbackend.services.CertifiedStudentServices;

@Controller
public class CertifiedStudentController {
@Autowired private CertifiedStudentServices certifiedStudentServices;
@MutationMapping
public ResponseEntity<String> giveCertificateToStudent(@Argument(name ="input")CertifiedStudentInput studentCertificate){
    return certifiedStudentServices.saveStudentCertificate(studentCertificate);
}
@MutationMapping
public ResponseEntity<String> saveListOfStudentCertificate(@Argument(name ="input")List<CertifiedStudentInput> students){
    return certifiedStudentServices.saveListStudentCertificate(students);
}
@MutationMapping
public ResponseEntity<String> certifyStudent(@Argument(name = "trainingApplicationId")long trainingApplicationId,@Argument(name = "applicationStatus")String TrainingApplicationStatus,@Argument(name = "certifyStudentInput")CertifiedStudentInput input){
    return certifiedStudentServices.studentCertificateApproval(trainingApplicationId,TrainingApplicationStatus,input);
}
@QueryMapping
public CertifiedStudentPage getStudentCertificatePage(@Argument(name = "studentId")long studentId,@Argument(name = "input")PaginationInput input){
    return certifiedStudentServices.findStudentCertificates(studentId,input);
}
@QueryMapping
public CertifiedStudentPage getCertifiedStudentPage(@Argument(name = "certificateId")long certificateId,@Argument(name="input")PaginationInput input){
    return certifiedStudentServices.findCertifiedStudentPage(certificateId,input);
}
}
