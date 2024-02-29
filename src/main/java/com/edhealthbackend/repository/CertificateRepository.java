package com.edhealthbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.Training;
public interface CertificateRepository extends JpaRepository<Certificate,Long>{

    List<Certificate> findAllByTraining(Training trainingId);
}
