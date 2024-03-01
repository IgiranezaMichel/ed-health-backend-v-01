package com.edhealthbackend.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Job;

public interface JobRepository extends JpaRepository<Job,Long>{
    Page<Job> findAllByHospitalAndStatus(Hospital hospitalId, String status, PageRequest of);

    Page<Job> findAllByDeadlineBefore(LocalDateTime now, PageRequest of);

    Page<Job> findAllByStatusAndDeadlineBefore(String status, LocalDateTime now, PageRequest of);

}
