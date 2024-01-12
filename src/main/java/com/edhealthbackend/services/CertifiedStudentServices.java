package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.CertifiedStudent;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.CertifiedStudentInput;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.CertifiedStudentPage;
import com.edhealthbackend.repository.CertificateRepository;
import com.edhealthbackend.repository.CertifiedStudentRepository;


@Service
public class CertifiedStudentServices extends DefaultRepositoryMethod<CertifiedStudent,Long>{
public CertifiedStudentServices(JpaRepository<CertifiedStudent, Long> jpaRepository) {
        super(jpaRepository);
    }
@Autowired private CertifiedStudentRepository certifiedStudentRepository;
@Autowired private CertificateServices certificateServices;
@Autowired private StudentServices studentServices;
@Autowired private CertificateRepository certificateRepository;
@Override
public String deleteById(Long id) {
   try {
    CertifiedStudent trainingCertificate=this.findById(id);
    certifiedStudentRepository.delete(trainingCertificate);
    return trainingCertificate.getCertificate().getTitle()+" Certificate rejected";
   } catch (Exception e) {
    return "Certificate not found";
   }
}

@Override
public List<CertifiedStudent> search(String search) {
    String searcString=search.toLowerCase();
  return certifiedStudentRepository.findAll().
  stream().
  filter(tc->(
tc.getCertificate().getDescription().toLowerCase().equals(searcString)
||tc.getTimeStamp().toString().equals(searcString)
  )).
  toList();
}

public CertifiedStudentPage findStudentCertificates(long studentId, PaginationInput input) {
    Student student=studentServices.findById(studentId);
    Page<CertifiedStudent>page=certifiedStudentRepository.findAllByStudent(student,PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSort())));
    return new CertifiedStudentPage(page.getContent(), page.getNumber(), page.getTotalPages(), page.getSize());
}

public ResponseEntity<String> saveStudentCertificate(CertifiedStudentInput studentCertificate) {
  Student student=studentServices.findById(studentCertificate.getStudentId());
  Certificate certificate=certificateServices.findById(studentCertificate.getCertificateId());
  CertifiedStudent certifiedStudent=this.saveOrUpdate(new CertifiedStudent(0, student, certificate, studentCertificate.getCertificateStatus(), LocalDateTime.now(), null));
  return new ResponseEntity<>(certifiedStudent.getStudent().getUser().getName()+" has given a certificate",HttpStatus.OK);
}

public ResponseEntity<String> saveListStudentCertificate(List<CertifiedStudentInput> students) {
List<CertifiedStudent>list=new ArrayList<>();
students.stream().forEach(certified->{
  list.add(new CertifiedStudent(0, studentServices.findById(certified.getCertificateId()), certificateServices.findById(certified.getCertificateId()), certified.getCertificateStatus(), LocalDateTime.now(), null));
});
certifiedStudentRepository.saveAll(list);
return new ResponseEntity<>("List of certificate saved successful",HttpStatus.OK);
}

public CertifiedStudentPage findCertifiedStudentPage(long certificateId, PaginationInput input) {
  Certificate certificate=new Certificate();
  boolean certificateIsFound=certificateRepository.existsById(certificateId);
  if(certificateIsFound)certificate.setId(certificateId);
  Page<CertifiedStudent>page=certifiedStudentRepository.findAllByCertificate(certificate,PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSort())));
  return new CertifiedStudentPage(page.getContent(), page.getNumber(), page.getTotalPages(),page.getTotalElements());
}
}
