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
import com.edhealthbackend.model.Job;
import com.edhealthbackend.model.JobApplication;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.JobApplicationPage;
import com.edhealthbackend.repository.JobApplicationRepository;

@Service
public class JobApplicationServices extends DefaultRepositoryMethod<JobApplication, Long> {
  public JobApplicationServices(JpaRepository<JobApplication, Long> jpaRepository) {
    super(jpaRepository);
  }

  @Autowired
  private JobApplicationRepository jobApplicationRepository;
  @Autowired
  private StudentServices studentServices;
  @Autowired private JobServices jobServices;
  @Override
  public String deleteById(Long id) {
    try {
      JobApplication jobApplication = this.findById(id);
      jobApplicationRepository.delete(jobApplication);
      return jobApplication.getJob().getTitle() + " ";
    } catch (Exception e) {
      return "Job Application not found";
    }
  }

  @Override
  public List<JobApplication> search(String search) {
    String joString = search.toLowerCase();
    return jobApplicationRepository.findAll()
        .stream().filter(j -> (j.getJob().getDeadline().toString().toLowerCase().equals(joString)
            || j.getJob().getDescription().toLowerCase().equals(joString)
            || j.getJob().getTitle().toLowerCase().equals(joString)))
        .toList();
  }

  public JobApplicationPage findStudentJobApplicationList(long studentId, String status, PaginationInput in) {
    Student student = studentServices.findById(studentId);
    Page<JobApplication> page = jobApplicationRepository.findAllByStudentAndStatus(student, status,
        PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
    return new JobApplicationPage(page.getContent(), page.getNumber(), page.getTotalPages(), page.getSize());
  }
  public ResponseEntity<String> registerJobApplication(long studentId, long jobId, String status) {
    Job job = jobServices.findById(jobId);
    Student student = studentServices.findById(studentId);
    if (job == null || student == null)
      return new ResponseEntity<>("Please specify correct job", HttpStatus.METHOD_NOT_ALLOWED);
    else {
      // find if job application is already exist
      JobApplication studentApplication = jobApplicationRepository.findByStudent(student);
      if (studentApplication == null) {
        JobApplication jobApplication = this
            .saveOrUpdate(new JobApplication(0, job, student, LocalDateTime.now(), status));
        return new ResponseEntity<>(
            jobApplication.getStudent().getUser().getName() + " Your application has been received successful",
            HttpStatus.OK);
      } else {
        studentApplication.setStatus(status);
        this.saveOrUpdate(studentApplication);
        return new ResponseEntity<>("Application " + status + " successful", HttpStatus.OK);
      }
    }
  }
  public JobApplicationPage findJobApplicationByJobIdAndJobStatus(long jobId, PaginationInput in, String status) {
    Job job=jobServices.findById(jobId);
    Page<JobApplication>page=jobApplicationRepository.findAllByJobAndStatus(job,status,PageRequest.of(in.getPageNumber(),in.getPageSize(), Sort.by(in.getSort())));
    return new JobApplicationPage(page.getContent(), page.getNumber(), page.getTotalPages(), page.getSize());
  }
}
