package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Job;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.JobPage;
import com.edhealthbackend.repository.JobRepository;
@Service
public class JobServices extends DefaultRepositoryMethod<Job,Long>{
public JobServices(JpaRepository<Job, Long> jpaRepository) {
      super(jpaRepository);
   }
@Autowired private JobRepository jobRepository;
@Autowired HospitalServices hospitalServices;
    @Override
    public String deleteById(Long id) {
       try {
         Job job=this.findById(id);
         jobRepository.delete(job);
         return job.getTitle()+" Deleted successful";
       } catch (Exception e) {
        return "Job not found";
       }
    }
    @Override
    public List<Job> search(String search) {
       return jobRepository.findAll().stream().
      filter(j->(j.getDeadline().toString().toLowerCase().contains(search.toLowerCase())
      ||j.getDescription().toLowerCase().equals(search.toLowerCase())
      ||j.getHospital().getName().toLowerCase().equals(search.toLowerCase())
      ||j.getTitle().toLowerCase().equals(search.toLowerCase())
      ))
       .toList();
    }
    public JobPage findJobsPostedByHospital(PaginationInput in, long hospitalId, String status) {
      Hospital hospital=hospitalServices.findHospitalById(hospitalId);
        Page<Job>page=jobRepository.findAllByHospitalAndStatus(hospital,status,PageRequest.of(in.getPageNumber(), in.getPageSize(),Sort.by(in.getSort())));
       return new JobPage(page.getContent(),page.getNumber(),page.getTotalPages(),page.getTotalElements());
}
    public JobPage findActiveJobsPostedPage(PaginationInput in, String status) {
      Page<Job>page=jobRepository.findAllByStatusAndDeadlineBefore(status,LocalDateTime.now(),PageRequest.of(in.getPageNumber(), in.getPageSize(),Sort.by(in.getSort())));
      return new JobPage(page.getContent(),page.getNumber(),page.getTotalPages(),page.getTotalElements());
    }
}
