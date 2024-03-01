package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

}
