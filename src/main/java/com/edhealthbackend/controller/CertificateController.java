package com.edhealthbackend.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.gql.InputDefs.CertificateInput;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.CertificatePage;
import com.edhealthbackend.services.CertificateServices;

@Controller
public class CertificateController {
@Autowired private CertificateServices certificateServices;
@MutationMapping
public ResponseEntity<String> saveCertificate(@Argument(name = "input")CertificateInput in){
return certificateServices.registerCertificate(in);
}
@MutationMapping
public ResponseEntity<String> deleteCertificate(@Argument(name = "id")long id){
try {
    return new ResponseEntity<>(certificateServices.deleteById(id),HttpStatus.OK);
} catch (Exception e) {
    return new ResponseEntity<>("Certificate doen't exist",HttpStatus.METHOD_NOT_ALLOWED);
}
}
@QueryMapping
public Certificate findCertificateById(@Argument(name ="id")long id){
        return certificateServices.findCertificateById(id); 
}
@QueryMapping
public CertificatePage certificatePage(@Argument(name ="input")PaginationInput in){
    return certificateServices.certificatePagination(in);
}
@QueryMapping
public List<Certificate> searchCertificates(@Argument(name = "search")String search){
    return certificateServices.search(search);
}
}
