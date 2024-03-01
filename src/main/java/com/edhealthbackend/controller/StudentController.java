package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.enums.StudentStatus;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.InputDefs.StudentInput;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.model.gql.pagination.StudentPage;
import com.edhealthbackend.services.StudentServices;

@Controller
public class StudentController {
@Autowired private StudentServices studentServices;
@MutationMapping
public String registerStudent(@Argument("input")StudentInput in,@Argument("user")AccountHolderInput user){
return studentServices.saveOrUpdateStudent(in,user);
}
@MutationMapping
public String deleteStudent(@Argument(name = "id")long id){
    return studentServices.deleteById(id);
}
@QueryMapping 
public Student findStudentById(@Argument(name = "id")long id){
    return studentServices.findById(id);
}
@QueryMapping
public StudentPage studentListPage(@Argument(name = "input")PaginationInput in){
return studentServices.studentPage(in);
}
@QueryMapping
public StudentPage studentFromSameSchoolListPage(@Argument(name="schoolId")long schoolId,@Argument(name = "input")PaginationInput in){
return studentServices.studentListPage(schoolId,in);
}
@QueryMapping
public StudentPage findStudentFromSameSchoolByStatusListPage(@Argument(name="schoolId")long schoolId,@Argument(name = "status")StudentStatus status,@Argument(name = "input")PaginationInput in){
return studentServices.findStudentFromSameSchoolByStatusListPage(schoolId,status,in);
}
@QueryMapping
public List<BarchartDTO<StudentStatus>> studentStatisticsByStatus(){
    return studentServices.studentStatisticsByStatus();
}
@QueryMapping
public List<BarchartDTO<StudentStatus>> studentFromSameSchoolStatisticsByStatus(@Argument(name = "schoolId")long schoolId){
    return studentServices.studentStatus(schoolId);
}
}
