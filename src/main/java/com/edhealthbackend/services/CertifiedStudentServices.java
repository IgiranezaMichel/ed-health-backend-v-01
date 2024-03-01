package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.CertifiedStudent;
import com.edhealthbackend.repository.CertifiedStudentRepository;


@Service
public class CertifiedStudentServices extends DefaultRepositoryMethod<CertifiedStudent,Long>{
public CertifiedStudentServices(JpaRepository<CertifiedStudent, Long> jpaRepository) {
        super(jpaRepository);
    }
@Autowired private CertifiedStudentRepository tCertificateRepository;
@Override
public String deleteById(Long id) {
   try {
    CertifiedStudent trainingCertificate=this.findById(id);
    tCertificateRepository.delete(trainingCertificate);
    return trainingCertificate.getCertificate().getTitle()+" Certificate rejected";
   } catch (Exception e) {
    return "Certificate not found";
   }
}

@Override
public List<CertifiedStudent> search(String search) {
    String searcString=search.toLowerCase();
  return tCertificateRepository.findAll().
  stream().
  filter(tc->(
tc.getCertificate().getDescription().toLowerCase().equals(searcString)
||tc.getTimeStamp().toString().equals(searcString)
  )).
  toList();
}
}
