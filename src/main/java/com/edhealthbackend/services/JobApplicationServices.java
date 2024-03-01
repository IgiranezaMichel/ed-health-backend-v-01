package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.JobApplication;
import com.edhealthbackend.repository.JobApplicationRepository;

@Service
public class JobApplicationServices extends DefaultRepositoryMethod<JobApplication, Long> {
  public JobApplicationServices(JpaRepository<JobApplication, Long> jpaRepository) {
    super(jpaRepository);
  }

  @Autowired
  private JobApplicationRepository jobApplicationRepository;

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
}
