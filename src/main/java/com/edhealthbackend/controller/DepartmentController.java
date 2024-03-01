package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.Department;
import com.edhealthbackend.model.Faculty;
import com.edhealthbackend.model.gql.InputDefs.DepartmentInput;
import com.edhealthbackend.services.DepartmentServices;
import com.edhealthbackend.services.FacultyServices;

@Controller
public class DepartmentController {
@Autowired private DepartmentServices departmentServices;
@Autowired private FacultyServices facultyServices;
@MutationMapping
public String registerDepartment(@Argument(name = "input")DepartmentInput in){
    Faculty faculty=facultyServices.findById(in.getFacultyId());
    return departmentServices.saveOrUpdate(new Department(in.getFacultyId(), in.getName(), in.getTotalCourse(), in.getTotalCredit(), faculty,null)).getName()+" Department registered successful";
}
@QueryMapping
public Department findDepartmentById(@Argument(name ="id")long id){
    return departmentServices.findById(id);
}
}
