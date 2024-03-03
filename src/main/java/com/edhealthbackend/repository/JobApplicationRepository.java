package com.edhealthbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.JobApplication;
import com.edhealthbackend.model.Student;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long>{

    Page<JobApplication> findAllByStudentAndStatus(Student student, String status, PageRequest of);

    JobApplication findByStudent(Student student);

}
