package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.enums.Role;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.HospitalAdmin;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.HospitalAdminInput;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.repository.HospitalAdminRepository;

@Service
public class HospitalAdminServices extends DefaultRepositoryMethod<HospitalAdmin,Long>{
public HospitalAdminServices(JpaRepository<HospitalAdmin, Long> jpaRepository) {
        super(jpaRepository);
    }

@Autowired private HospitalAdminRepository hospitalAdminRepository;
@Autowired private AccountHolderServices userServices;
@Autowired private HospitalServices hospitalServices;

@Override
public List<HospitalAdmin> search(String search) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'search'");
}

@Override
public String deleteById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
}

public ResponseEntity<String> registerHospitalAdmin(HospitalAdminInput in, AccountHolderInput user) {
    AccountHolder user1=userServices.findByEmail(user.getEmail());
    if(user1==null&&!user.getName().equals("")&&!user.getPhoneNumber().equals("")){
        user1=userServices.saveOrUpdate(new AccountHolder(user.getId(), user.getName(), user.getGender(), user.getEmail(), user.getPhoneNumber(), user.getProfilePicture(), user.getDob(), user.getPassword(), Role.HOSPITAL_ADMIN, null, null, null,null));
    }
    if(user1==null&&user.getName().equals("")&&user.getPhoneNumber().equals("")&&user.getProfilePicture().equals(""))return new ResponseEntity<>("Please Add valid user data",HttpStatus.METHOD_NOT_ALLOWED);
    Hospital hospital=hospitalServices.findHospitalById(in.getHospitalId());
    if(hospital==null)return new ResponseEntity<>("Please specify correct hospital",HttpStatus.METHOD_NOT_ALLOWED);
    else{
       HospitalAdmin hospitalAdmin=hospitalAdminRepository.save(new HospitalAdmin(in.getId(),hospital, user1,in.getPosition(),LocalDateTime.now(),in.getEndDate(),in.getStatus()));
       return new ResponseEntity<>(hospitalAdmin.getUser().getName()+" has registered as "+hospitalAdmin.getHospital().getName()+" admin successful",HttpStatus.OK);
    }
}

public List<BarchartDTO<HospitalAdmin>> findHospitalAdminStatisticsByAdminStatus() {
   return hospitalAdminRepository.findHospitalAdminStatisticsByAdminStatus();
}

}
