package com.edhealthbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.TrainingApplication;

public interface TrainingApplicationRepository extends JpaRepository<TrainingApplication,Long>{

    Page<TrainingApplication> findAllByTraining(Training training, PageRequest of);
    Page<TrainingApplication> findAllByTrainingAndHospitalApprovalStatus(Training training, String status,
            PageRequest of);

}
