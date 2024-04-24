package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.JobApplication;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.JobApplicationPage;
import com.edhealthbackend.services.JobApplicationServices;
@Controller
public class JobApplicationController {
@Autowired private JobApplicationServices jobApplicationServices;
@MutationMapping
public ResponseEntity<String>registerStudentJobApplication(@Argument(name = "studentId")long studentId,@Argument(name = "jobId")long jobId,@Argument(name = "status")String status){
    return jobApplicationServices.registerJobApplication(studentId,jobId,status);
}
@MutationMapping
public ResponseEntity<String>changeJobApplicantStatusByHospitalAdmin(@Argument(name = "id")long JobApplicationId,@Argument(name = "status")String status){
    return jobApplicationServices.changeApplicantStatusByHospitalAdmin(JobApplicationId,status);
}
@QueryMapping
 public JobApplicationPage getStudentJobApplicationList(@Argument(name = "studentId")long studentId,@Argument(name = "status")String status,@Argument(name = "input")PaginationInput in){
   return jobApplicationServices.findStudentJobApplicationList(studentId,status,in);
}
@QueryMapping
 public JobApplication getJobApplicationDetail(@Argument(name = "id")long id){
   return jobApplicationServices.findById(id);
}
@QueryMapping
public JobApplicationPage findJobApplicationByJobIdAndJobStatus(@Argument(name ="jobId")long jobId,@Argument(name ="input")PaginationInput in,@Argument(name ="status")String status){
    return jobApplicationServices.findJobApplicationByJobIdAndJobStatus(jobId,in,status);
}
@QueryMapping
public List<JobApplication> getJobApplicationListByUserId(@Argument(name = "userId")long id){
    return jobApplicationServices.findUserJobApplicationList(id);
}
}
