package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.Faculty;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.gql.InputDefs.FacultyInput;
import com.edhealthbackend.services.FacultyServices;
import com.edhealthbackend.services.SchoolServices;

@Controller
public class FacultyController {
@Autowired private FacultyServices facultyServices;
@Autowired private SchoolServices schoolServices;
@MutationMapping
public String registerFaculty(@Argument(name = "input")FacultyInput input){
  School school=schoolServices.findById(input.getSchoolId());
    return facultyServices.saveOrUpdate(new Faculty(input.getId(), input.getName(), school)).getName()+" Saved successful";
}
}
