package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.model.SchoolAdmin;
import com.edhealthbackend.model.gql.InputDefs.AdminInput;
import com.edhealthbackend.model.gql.InputDefs.SchoolAdminInput;
import com.edhealthbackend.services.SchoolAdminServices;

@Controller
public class SchoolAdminController {
    @Autowired
    private SchoolAdminServices schoolAdminServices;
    @MutationMapping
    public String registerSchoolAdmin(@Argument(name = "input") SchoolAdminInput input,@Argument(name = "adminInput")AdminInput admin) {
        return schoolAdminServices.registerSchoolAdmin(input,admin);
    }
    @MutationMapping
    public String deleteSchoolAdminById(@Argument(name = "id") long id) {
        return schoolAdminServices.deleteSchoolAdminById(id);
    }
    @QueryMapping
    public List<BarchartDTO<SchoolAdmin>> schoolAdminStatusStatistics(){
        return schoolAdminServices.schoolAdminStatus();
    }
}
