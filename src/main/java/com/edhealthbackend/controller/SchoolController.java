package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.model.gql.InputDefs.Pagination;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.InputDefs.SchoolInput;
import com.edhealthbackend.model.gql.pagination.SchoolPage;
import com.edhealthbackend.services.SchoolServices;

@Controller
public class SchoolController {
@Autowired private SchoolServices schoolServices;
@MutationMapping
public String registerSchoolForExistingAccountHolder(@Argument(name = "schoolInput")SchoolInput in,@Argument(name = "userEmail")String email){
return schoolServices.saveSchoolByAddingExistingUser(in,email);
}
@MutationMapping
public String registerSchoolForNewAccountHolder(@Argument(name = "schoolInput")SchoolInput in,@Argument(name = "userInput")AccountHolderInput user){
    return schoolServices.saveNewSchoolAndUser(in,user);
}
@MutationMapping
public String deleteSchool(@Argument(name = "id")long id){
return schoolServices.deleteById(id);
}
@QueryMapping
public School findSchoolById(@Argument(name="id")long id){
    return schoolServices.findById(id);
}
@QueryMapping
public SchoolPage schoolPageList(@Argument(name = "input")PaginationInput in){
    Pagination<School>page=schoolServices.pagination(in);
    return new SchoolPage(page.getContent(), page.getPageNumber(), page.getTotalPages(),page.getSize());
}
}
