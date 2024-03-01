package com.edhealthbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.model.Trainer;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.TrainerInput;
import com.edhealthbackend.services.TrainerServices;
import com.edhealthbackend.services.TrainingServices;

@Controller
public class TrainerController {
    @Autowired
    private TrainerServices trainerServices;
    @Autowired
    private TrainingServices trainingServices;

    @MutationMapping
    public String registerTrainer(@Argument(name = "input") TrainerInput trainer) {
        try {
            Training training=trainingServices.findTrainingById(trainer.getTrainingId());
             trainerServices.savaTrainer(new Trainer(trainer.getId(), trainer.getTrainerTitle(),
            trainer.getName(), trainer.getPhoneNumber(), trainer.getProfilePicture(), trainer.getSignature(),training));
            return "Training saved successful";
        } catch (Exception e) {
           return e.getMessage();
        }

    }

    @MutationMapping
    public String deleteTrainerById(@Argument(name = "id") long id) {
        return trainerServices.deleteTrainer(id);
    }

    @QueryMapping
    public List<Trainer> searchTrainer(@Argument(name = "search") String search) {
        return trainerServices.searchTrainer(search);
    }
}
