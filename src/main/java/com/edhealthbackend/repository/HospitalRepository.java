package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long>{

}
