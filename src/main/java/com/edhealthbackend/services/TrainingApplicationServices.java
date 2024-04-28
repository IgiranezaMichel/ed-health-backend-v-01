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
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.TrainingApplication;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.TrainingApplicationPage;
import com.edhealthbackend.repository.TrainingApplicationRepository;

@Service
public class TrainingApplicationServices extends DefaultRepositoryMethod<TrainingApplication, Long> {
  public TrainingApplicationServices(JpaRepository<TrainingApplication, Long> jpaRepository) {
    super(jpaRepository);
  }

  @Autowired
  private TrainingApplicationRepository tApplicationRepo;
  @Autowired
  private TrainingServices trainingServices;
  @Autowired
  private StudentServices studentServices;

  @Override
  public String deleteById(Long id) {
    try {
      TrainingApplication trainingApplication = this.findById(id);
      tApplicationRepo.delete(trainingApplication);
      return "Training Application removed successful";
    } catch (Exception e) {
      return "Training Apllication not found";
    }
  }

  @Override
  public List<TrainingApplication> search(String search) {
    return tApplicationRepo.findAll().stream()
        .filter(t -> (t.getHospitalApprovalStatus().toLowerCase().equals(search.toLowerCase())
            || t.getStudentApprovalStatus().toLowerCase().equals(search.toLowerCase())
            || t.getTraining().getHospital().getName().toLowerCase().equals(search.toLowerCase())

        ))
        .toList();
  }

  public TrainingApplicationPage findTrainingApplicantPageByHospitalAdmin(String status, long trainingId,
      PaginationInput in) {
    Training training = trainingServices.findTrainingById(trainingId);
    Page<TrainingApplication> page = tApplicationRepo.findAllByTrainingAndHospitalApprovalStatus(training, status,
        PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
    return new TrainingApplicationPage(page.getContent(), page.getNumber(), page.getTotalPages(),
        page.getTotalElements());
  }

  public ResponseEntity<String> changeApplicantStatusByHospitalAdmin(long applicationId,
      String trainingApplicationStatus) {
    try {
      TrainingApplication trainingApplication = tApplicationRepo.findById(applicationId).orElseThrow();
      trainingApplication.setHospitalApprovalStatus(trainingApplicationStatus);
      trainingApplication.setHospitalApprovalTimeStamp(LocalDateTime.now());
      TrainingApplication tApplication = tApplicationRepo.save(trainingApplication);
      return new ResponseEntity<>(
          tApplication.getStudent().getUser().getName() + " " + trainingApplicationStatus + " saved successful",
          HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Please provide correct information", HttpStatus.METHOD_NOT_ALLOWED);
    }
  }

  public ResponseEntity<String> saveStudentTrainingRegistration(long studentId, long trainingId, String approval) {
    try {
      Student student = studentServices.findById(studentId);
      Training training = trainingServices.findTrainingById(trainingId);
      if (student == null)
        throw new Exception("Student not found");
      if (training == null)
        throw new Exception("No such training found");
      TrainingApplication trainingApplication =tApplicationRepo.findByStudent(student);
      if (trainingApplication == null) {
        trainingApplication=new TrainingApplication(0, approval, null, "appending", LocalDateTime.now(), training, student);
        trainingApplication.setStudentApprovalStatus(approval);
        trainingApplication= this.saveOrUpdate(trainingApplication);
        return new ResponseEntity<>(trainingApplication.getStudent().getUser().getName() + " Your status is well received",
            HttpStatus.OK);
      } else {
        if(approval.equals("appending")){
          return new ResponseEntity<>("You've already applied  for this training",HttpStatus.OK);
        }
        trainingApplication.setStudentApprovalTimeStamp(LocalDateTime.now());
        trainingApplication.setStudentApprovalStatus(approval);
        this.saveOrUpdate(trainingApplication);
        return new ResponseEntity<>(" Your request has registered successful", HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
  }
  public TrainingApplicationPage getStudentTrainingApplication(long studentId, String status,PaginationInput in) {
    Student student=studentServices.findById(studentId);
    Page<TrainingApplication> page = tApplicationRepo.findAllByStudentAndHospitalApprovalStatus(student, status,
    PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
    return new TrainingApplicationPage(page.getContent(), page.getNumber(), page.getTotalPages(),
        page.getTotalElements());
  }
  public List<TrainingApplication> getListOfAllTrainingApplicant(long trainingId) {
    Training training=trainingServices.findTrainingById(trainingId);
    return tApplicationRepo.findAllByTraining(training);
     
  }
}
