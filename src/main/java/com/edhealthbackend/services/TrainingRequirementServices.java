package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.TrainingRequirement;
import com.edhealthbackend.repository.TrainingRequirementRepository;

@Service
public class TrainingRequirementServices extends DefaultRepositoryMethod<TrainingRequirement,Long>{
    public TrainingRequirementServices(JpaRepository<TrainingRequirement, Long> jpaRepository) {
        super(jpaRepository);
    }
    @Autowired
    private TrainingRequirementRepository tRepository;

    public void saveListOfRequirement(List<TrainingRequirement> data) {
        tRepository.saveAll(data);
    }
    @Override
    public List<TrainingRequirement> search(String search) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    @Override
    public String deleteById(Long id) {
        try {
            TrainingRequirement tr = this.findById(id);
            tRepository.delete(tr);
            return "Requirement removed successful";
        } catch (Exception e) {
            return "Requirement not found";
        }
    }
}
