package com.edhealthbackend.services;

import java.time.LocalDateTime;
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
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.TrainingApplication;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.TrainingApplicationPage;
import com.edhealthbackend.repository.TrainingApplicationRepository;

@Service
public class TrainingApplicationServices extends DefaultRepositoryMethod<TrainingApplication,Long>{
public TrainingApplicationServices(JpaRepository<TrainingApplication, Long> jpaRepository) {
    super(jpaRepository);
  }

@Autowired private TrainingApplicationRepository tApplicationRepo;
@Autowired private TrainingServices trainingServices;
    @Override
    public String deleteById(Long id) {
        try {
        TrainingApplication trainingApplication=this.findById(id);
        tApplicationRepo.delete(trainingApplication);
        return "Training Application removed successful";
        } catch (Exception e) {
          return "Training Apllication not found";
        }
    }
    @Override
    public List<TrainingApplication> search(String search) {
       return tApplicationRepo.findAll().stream().
       filter(t->(
        t.getHospitalApprovalStatus().toLowerCase().equals(search.toLowerCase())
        ||t.getStudentApprovalStatus().toLowerCase().equals(search.toLowerCase())
        ||t.getTraining().getHospital().getName().toLowerCase().equals(search.toLowerCase())

       ))
       .toList();
    }
    public TrainingApplicationPage findTrainingApplicantPageByHospitalAdmin(String status, long trainingId, PaginationInput in) {
      Training training=trainingServices.findTrainingById(trainingId);
      Page<TrainingApplication>page=tApplicationRepo.findAllByTrainingAndHospitalApprovalStatus(training,status,PageRequest.of(in.getPageNumber(),in.getPageSize(), Sort.by(in.getSort())));
      return new TrainingApplicationPage(page.getContent(), page.getNumber(), page.getTotalPages(),page.getTotalElements());  
    }
    public ResponseEntity<String> changeApplicantStatusByHospitalAdmin(long trainingApplicationId, String trainingApplicationStatus) {
      try {
        TrainingApplication trainingApplication=tApplicationRepo.findById(trainingApplicationId).orElseThrow();
        trainingApplication.setHospitalApprovalStatus(trainingApplicationStatus);
        trainingApplication.setHospitalApprovalTimeStamp(LocalDateTime.now());
        TrainingApplication tApplication=tApplicationRepo.save(trainingApplication);
        return new ResponseEntity<>(tApplication.getStudent().getUser().getName()+" "+trainingApplicationStatus+" saved successful",HttpStatus.OK);
      } catch (Exception e) {
       return new ResponseEntity<>("Please provide correct information",HttpStatus.METHOD_NOT_ALLOWED);
      }
    }
}
