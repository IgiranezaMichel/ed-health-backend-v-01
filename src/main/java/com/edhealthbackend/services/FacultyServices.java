package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Faculty;
import com.edhealthbackend.repository.FacultyRepository;
@Service
public class FacultyServices extends DefaultRepositoryMethod<Faculty,Long>{
public FacultyServices(JpaRepository<Faculty, Long> jpaRepository) {
        super(jpaRepository);
    }
@Autowired private FacultyRepository facultyRepository;
    @Override
    public String deleteById(Long id) {
       try {
       Faculty faculty= this.findById(id);
       facultyRepository.delete(faculty);
       return faculty.getName()+" Deleted successful";
       } catch (Exception e) {
        return "Faculty not found";
       }
    }
    @Override
    public List<Faculty> search(String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    } 
}
