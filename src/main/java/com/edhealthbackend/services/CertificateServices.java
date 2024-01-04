package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.CertificateInput;
import com.edhealthbackend.model.gql.InputDefs.Pagination;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.CertificatePage;
import com.edhealthbackend.repository.CertificateRepository;
import com.edhealthbackend.repository.TrainingRepository;
@Service
public class CertificateServices extends DefaultRepositoryMethod<Certificate,Long>{
public CertificateServices(JpaRepository<Certificate, Long> jpaRepository) {
        super(jpaRepository);
    }
@Autowired private CertificateRepository certificateRepository;
@Autowired private TrainingRepository trainingRepository;
@Autowired private AccountHolderServices accountHolderServices;
    public String deleteById(Long id) {
    try {
        Certificate certificate=this.findById(id);
        certificateRepository.delete(certificate);
        return certificate.getTitle();
    } catch (Exception e) {
        return "Certificate not found";
    }
    }
    @Override
    public List<Certificate> search(String search) {
        return certificateRepository.findAll().stream().
        filter(c->(
            c.getTitle().toLowerCase().equals(search.toLowerCase())
            ||c.getDescription().toLowerCase().equals(search.toLowerCase())
            ||c.getTimeStamp().toString().toLowerCase().equals(search.toLowerCase())
        )).toList();
    }
    public CertificatePage certificatePagination(PaginationInput in){
        Pagination<Certificate>certificatePagination= pagination(in);
        CertificatePage certificatePage=new CertificatePage(certificatePagination.getContent(), certificatePagination.getPageNumber(), certificatePagination.getTotalPages(), certificatePagination.getSize());
        return certificatePage;
    }
    
    public ResponseEntity<String> registerCertificate(CertificateInput in) {
    try {
        Training training=trainingRepository.findById(in.getTrainingId()).orElseThrow();
        AccountHolder accountHolder=accountHolderServices.findById(in.getAccountHolderId());
        
        return new ResponseEntity<>("Certificate "+this.saveOrUpdate(new Certificate(in.getId(),in.getTitle(), in.getDescription(), in.getUserSignature(), in.getHospitalStamp(), LocalDateTime.now(), training,accountHolder)).getTitle()+" Saved successfully",HttpStatus.OK); 
    } catch (Exception e) {
      return new ResponseEntity<>("Training not found",HttpStatus.NOT_ACCEPTABLE);
    }
    }
    public Certificate findCertificateById(long id) {
        return certificateRepository.findById(id).orElse(null);
       }
    public List<Certificate> findCertificateByTrainingId(long trainingId) {
        // Training training=trainingRepository.findById(trainingId).orElse(null);
    certificateRepository.findAll().stream().forEach(em->System.out.println(em.getTitle()));
        return certificateRepository.findAll();
    }
}
