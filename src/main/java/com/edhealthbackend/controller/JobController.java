package com.edhealthbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Job;
import com.edhealthbackend.model.gql.InputDefs.JobInput;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.JobPage;
import com.edhealthbackend.services.HospitalServices;
import com.edhealthbackend.services.JobServices;

@Controller
public class JobController{
@Autowired private JobServices jobServices;
@Autowired private HospitalServices hospitalServices;
@MutationMapping
public String registerJob(@Argument(name = "input")JobInput in){
    Hospital hospital=new Hospital();
   try {
      hospital=hospitalServices.findHospitalById(in.getHospitalId());
   } catch (Exception e) {
   hospital=null;
   }
   return jobServices.saveOrUpdate(new Job(in.getId(),in.getTitle(),in.getDescription(),in.getDeadline(),hospital,in.getPicture(),in.getStatus(),in.getNumberOfEmployee(),in.getJobRequirement())).getTitle()+" saved successfully";
}
@QueryMapping
public JobPage findJobsPostedByHospital(@Argument(name = "input")PaginationInput in,@Argument(name = "hospitalId")long hospitalId,@Argument(name = "status")String status){
   return jobServices.findJobsPostedByHospital(in,hospitalId,status);
}
@QueryMapping
public JobPage findJobsPostedPage(@Argument(name = "input")PaginationInput in,@Argument(name = "status")String status){
   return jobServices.findActiveJobsPostedPage(in,status);
}
@QueryMapping 
public Job findJobById(@Argument(name = "id")long id){
   return jobServices.findById(id);
}
}
