package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.edhealthbackend.enums.Role;
import com.edhealthbackend.enums.Status;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.SchoolAdmin;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.model.gql.InputDefs.SchoolInput;
import com.edhealthbackend.repository.AccountHolderRepository;
import com.edhealthbackend.repository.SchoolAdminRepository;
import com.edhealthbackend.repository.SchoolRepository;
@Service
public class SchoolServices extends DefaultRepositoryMethod<School,Long>{
public SchoolServices(JpaRepository<School, Long> jpaRepository) {
    super(jpaRepository);
  }

@Autowired private SchoolRepository schoolRepository;
@Autowired private AccountHolderRepository accountHolderRepository;
@Autowired private SchoolAdminRepository schoolAdminRepository;
@Autowired private LocationServices locationServices;
    @Override
    public String deleteById(Long id) {
      try {
         School school=this.findById(id);
         schoolRepository.delete(school);
         return school.getName()+" deleted successful";
      } catch (Exception e) {
        return "School not found";
      }
    }
    @Override
    public List<School> search(String search) {
        return schoolRepository.findAll().stream()
        .filter(s->(
        s.getName().toLowerCase().equals(search.toLowerCase())
        ||s.getLocation().getName().toLowerCase().equals(search.toLowerCase())
        ))
        .toList();
    }
    public String saveNewSchoolAndUser(SchoolInput in, AccountHolderInput user) {
      try {
        boolean emailExist=accountHolderRepository.existsByEmail(user.getEmail());
        if(emailExist)throw new Exception("User Account already exist try again");
        AccountHolder accountHolder=accountHolderRepository.save(new AccountHolder(user.getId(),user.getName(), user.getGender(), user.getEmail(), user.getPhoneNumber(), user.getProfilePicture(), user.getDob(), user.getPassword(),Role.SCHOOL_ADMIN, null, null,null,null));
        School school= new School(in.getId(), in.getName(), in.getLogo(), locationServices.findLocationById(in.getLocationId()));
        schoolAdminRepository.save(new SchoolAdmin(0, school, accountHolder,"Admin",LocalDateTime.now(),null,Status.ACTIVE));
        return school.getName()+" has saved successful"; 
      } catch (Exception e) {
        return e.getMessage();
      }
    }
    public String saveSchoolByAddingExistingUser(SchoolInput in, String email) {
      AccountHolder accountHolder=accountHolderRepository.findByEmail(email);
      if(accountHolder!=null){
        accountHolder.setRole(Role.HOSPITAL_ADMIN);
        School school= new School(in.getId(), in.getName(), in.getLogo(), locationServices.findLocationById(in.getLocationId()));
        schoolAdminRepository.save(new SchoolAdmin(0, school, accountHolder,"Admin",LocalDateTime.now(),null,Status.ACTIVE));
        return school.getName()+" has saved successful";
      }
      return "Fails to register un existing user";
    }

}
