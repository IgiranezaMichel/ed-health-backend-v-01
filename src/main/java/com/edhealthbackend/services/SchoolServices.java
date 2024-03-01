package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.School;
import com.edhealthbackend.repository.SchoolRepository;
@Service
public class SchoolServices extends DefaultRepositoryMethod<School,Long>{
public SchoolServices(JpaRepository<School, Long> jpaRepository) {
    super(jpaRepository);
  }

@Autowired private SchoolRepository schoolRepository;
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

}
