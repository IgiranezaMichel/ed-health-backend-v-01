package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.CertifiedStudent;
public interface CertifiedStudentRepository extends JpaRepository<CertifiedStudent,Long>{

}
