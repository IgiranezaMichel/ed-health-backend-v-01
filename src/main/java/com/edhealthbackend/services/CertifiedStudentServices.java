package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.CertifiedStudent;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.CertifiedStudentPage;
import com.edhealthbackend.repository.CertifiedStudentRepository;


@Service
public class CertifiedStudentServices extends DefaultRepositoryMethod<CertifiedStudent,Long>{
public CertifiedStudentServices(JpaRepository<CertifiedStudent, Long> jpaRepository) {
        super(jpaRepository);
    }
@Autowired private CertifiedStudentRepository certifiedStudentRepository;
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
@Autowired private StudentServices studentServices;
public CertifiedStudentPage findStudentCertificates(long studentId, PaginationInput input) {
    Student student=studentServices.findById(studentId);
    Page<CertifiedStudent>page=certifiedStudentRepository.findAllByStudent(student,PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSort())));
    return new CertifiedStudentPage(page.getContent(), page.getNumber(), page.getTotalPages(), page.getSize());
}
}
