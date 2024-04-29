package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.SchoolAdmin;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.AdminInput;
import com.edhealthbackend.model.gql.InputDefs.SchoolAdminInput;
import com.edhealthbackend.repository.SchoolAdminRepository;

@Service
public class SchoolAdminServices extends DefaultRepositoryMethod<SchoolAdmin,Long>{
public SchoolAdminServices(JpaRepository<SchoolAdmin, Long> jpaRepository) {
        super(jpaRepository);
    }

@Autowired private SchoolAdminRepository schoolAdminRepository;
@Autowired private AccountHolderServices userServices;
@Autowired private SchoolServices schoolServices;
    @Override
    public List<SchoolAdmin> search(String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public String deleteById(Long id) {
       SchoolAdmin admin= this.findById(id);
       schoolAdminRepository.deleteById(id);
       return admin.getSchool().getName()+" From position "+admin.getPosition()+" has removed successful";
    }

    public String deleteSchoolAdminById(long id) {
       try {
        schoolAdminRepository.deleteById(id);
    return "School Admin removed successful";
    } catch (Exception e) {
     return "Please specify a correct school admin";
    }
    }

    public String registerSchoolAdmin(SchoolAdminInput input,AdminInput admin) {
      try {
    AccountHolder user=userServices.findById(input.getUserId());
    School school=schoolServices.findById(input.getSchoolId());
    return this.saveOrUpdate(new SchoolAdmin(input.getId(), school, user,admin.getPosition(),admin.getStartingDate(),admin.getEndDate(),admin.getStatus())).getSchool().getName()+" has registered successful ";
} catch (Exception e) {
    return "Please specify correct information";
}
    }

    public List<BarchartDTO<SchoolAdmin>> schoolAdminStatus() {
       return schoolAdminRepository.schoolAdminStatusStatistics();
    }
}
