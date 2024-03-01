package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edhealthbackend.model.Trainer;
import com.edhealthbackend.repository.TrainerRepository;
@Service
public class TrainerServices {
@Autowired private TrainerRepository trainerRepository;
public Trainer savaTrainer(Trainer trainer){
    return trainerRepository.save(trainer);
}
public Trainer findTrainerById(long id){
    return trainerRepository.findById(id).orElseThrow();
}
public String deleteTrainer(long id){
    try {
        Trainer trainer=this.findTrainerById(id);
        trainerRepository.deleteById(id);
        return trainer.getTrainerTitle()+" "+trainer.getName()+" Deleted successfully";
    } catch (Exception e) {
        return "Training not found";
    }
}
public List<Trainer> searchTrainer(String search) {
   List<Trainer>trainers=trainerRepository.findAll();
   List<Trainer>searchTraining=trainers.stream().filter(trainer->(
    trainer.getName().toLowerCase().contains(search.toLowerCase())
    ||trainer.getPhoneNumber().toLowerCase().contains(search.toLowerCase())
    ||trainer.getTrainerTitle().toLowerCase().contains(search.toLowerCase())
    ||trainer.getTraining().getHospital().getName().toLowerCase().contains(search.toLowerCase())
   )).toList();
   return searchTraining;
}
}
