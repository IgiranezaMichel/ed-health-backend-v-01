package com.edhealthbackend.controller;

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
@QueryMapping
public CertifiedStudentPage findStudentCertificates(@Argument(name = "studentId")long studentId,PaginationInput input){
    return certifiedStudentServices.findStudentCertificates(studentId,input);
}
}
