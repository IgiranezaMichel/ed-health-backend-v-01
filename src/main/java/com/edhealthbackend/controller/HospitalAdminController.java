package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.gql.InputDefs.HospitalAdminInput;
import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.model.HospitalAdmin;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.services.HospitalAdminServices;

@Controller
public class HospitalAdminController {
@Autowired private HospitalAdminServices hospitalAdminServices;
@MutationMapping
public ResponseEntity<String> registerHospitalAdmin(@Argument(name = "hospitalAdmin")HospitalAdminInput in,@Argument(name = "user")AccountHolderInput user){
    return hospitalAdminServices.registerHospitalAdmin(in,user);
}
public List<BarchartDTO<HospitalAdmin>> hospitalAdminStatisticByStatus(){
    return hospitalAdminServices.findHospitalAdminStatisticsByAdminStatus();
}
}
