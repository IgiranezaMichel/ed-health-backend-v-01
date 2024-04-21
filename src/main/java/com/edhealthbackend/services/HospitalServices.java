package com.edhealthbackend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.HospitalPage;
import com.edhealthbackend.model.gql.pagination.TrainingPage;
import com.edhealthbackend.repository.HospitalRepository;
import com.edhealthbackend.repository.TrainingRepository;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class HospitalServices {
@Autowired private HospitalRepository hospitalRepository;
@Autowired private TrainingRepository trainingRepository;
public Hospital saveOrUpdateHospital(Hospital hospital){
    return hospitalRepository.save(hospital);
}
public Hospital findHospitalById(long id){
    return hospitalRepository.findById(id).orElse(null);
}
public String deleteById(long id) throws Exception{
try {
Hospital hospital=this.findHospitalById(id);
if(hospital==null)throw new Exception("Hospital not found");
else{
hospitalRepository.deleteById(id);
return hospital.getName()+" deleted successfully";
}}
 catch (Exception e) {
return e.getMessage();
}
}
public List<Hospital> getAllHospital(String sort) {
   return hospitalRepository.findAll(Sort.by(sort));
}
public List<Hospital>getALlHospital(){
    return hospitalRepository.findAll();
}
public List<Hospital> searchHospital(String search) {
    List<Hospital>hospitalList=this.getALlHospital();
    List<Hospital>searchResult=hospitalList.stream()
    .filter(hospital->(hospital.getName().toLowerCase().contains(search.toLowerCase())
    ||hospital.getDescription().toLowerCase().contains(search.toLowerCase())
    ||hospital.getLocation().getLocation().getName().toLowerCase().contains(search.toLowerCase())
    ||hospital.getTimeStamp().toString().toLowerCase().contains(search.toLowerCase())
    ))
    .toList();
    return searchResult;
}
public HospitalPage hospitalPagination(PaginationInput input){
Page<Hospital>page=hospitalRepository.findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(),Sort.by(input.getSort())));
 return new HospitalPage(page.getContent(), page.getNumber(), page.getTotalPages(),
 this.hospitalSize());
}
private long hospitalSize() {
  return hospitalRepository.count();
}
public long findAvailableTrainingForHospital(Hospital hospital){
    return trainingRepository.countByHospital(hospital);

}
 public TrainingPage findListOfTrainingByHospitalIdAndNcnmApprovalStatus(long hospitalId,
        String ncnmApprovalStatus, PaginationInput input) {
        Hospital hospital=this.findHospitalById(hospitalId);
        Page<Training>page=trainingRepository.findAllByHospitalAndNcnmApprovalStatus(hospital,ncnmApprovalStatus,PageRequest.of(input.getPageNumber(),input.getPageSize(), Sort.by(input.getSort())));
        return new TrainingPage(page.getContent(), page.getNumber(), page.getTotalPages(),page.getTotalElements());  
    }
public TrainingPage findListOfNcnmApprovalStatusBeforeDeadLine(String ncnmApprovalStatus, PaginationInput input) {
Page<Training>page=trainingRepository.findAllByDeadlineBeforeAndNcnmApprovalStatus(LocalDateTime.now(),ncnmApprovalStatus,PageRequest.of(input.getPageNumber(),input.getPageSize(), Sort.by(input.getSort())));
return new TrainingPage(page.getContent(), page.getNumber(), page.getTotalPages(),page.getTotalElements());  
}
public TrainingPage findListOfNcnmApprovalStatusAfterDeadLine(String ncnmApprovalStatus, PaginationInput input) {
    Page<Training>page=trainingRepository.findAllByDeadlineAfterAndNcnmApprovalStatus(LocalDateTime.now(),ncnmApprovalStatus,PageRequest.of(input.getPageNumber(),input.getPageSize(), Sort.by(input.getSort())));
    return new TrainingPage(page.getContent(), page.getNumber(), page.getTotalPages(),page.getTotalElements());  
    }
public long totalHospital() {
  return hospitalRepository.count();
}
}
