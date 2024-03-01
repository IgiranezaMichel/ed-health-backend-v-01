package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Job;
import com.edhealthbackend.model.JobRequirement;
import com.edhealthbackend.model.gql.InputDefs.JobRequirementInput;
import com.edhealthbackend.repository.JobRequirementRepository;

@Service
public class JobRequirementServices extends DefaultRepositoryMethod<JobRequirement, Long> {
    public JobRequirementServices(JpaRepository<JobRequirement, Long> jpaRepository) {
        super(jpaRepository);
    }

    @Autowired
    private JobRequirementRepository jobRequirementRepository;
    @Autowired private JobServices jobServices;
    public void saveListOfJobRequirement(List<JobRequirement> data) {
        jobRequirementRepository.saveAll(data);
    }

    @Override
    public String deleteById(Long id) {
        try {
            JobRequirement jobRequirement = this.findById(id);
            jobRequirementRepository.delete(jobRequirement);
            return jobRequirement.getDescription() + " deleted successful";
        } catch (Exception e) {
            return "no such job desccription";
        }
    }

    @Override
    public List<JobRequirement> search(String search) {
        String searcString=search.toLowerCase();
      return jobRequirementRepository.findAll().stream().
      filter(jr->(
        jr.getDescription().toLowerCase().equals(searcString)
        ||jr.getJob().getDescription().toLowerCase().equals(searcString)
      ))
      .toList();
    }

    public String saveJobRequirement(JobRequirementInput in) {
        Job job=jobServices.findById(in.getJobId());
        this.saveOrUpdate(new JobRequirement(in.getId(),job,in.getDescription()));
         return "Requirement saved successful";
    }
}
