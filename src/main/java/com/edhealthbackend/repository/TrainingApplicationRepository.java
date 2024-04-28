package com.edhealthbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.TrainingApplication;

public interface TrainingApplicationRepository extends JpaRepository<TrainingApplication,Long>{

    Page<TrainingApplication> findAllByTraining(Training training, PageRequest of);
    List<TrainingApplication> findAllByTraining(Training training);
    Page<TrainingApplication> findAllByTrainingAndHospitalApprovalStatus(Training training, String status,
            PageRequest of);
    TrainingApplication findByStudent(Student student);
    Page<TrainingApplication> findAllByStudentAndHospitalApprovalStatus(Student student, String status, PageRequest of);

}
