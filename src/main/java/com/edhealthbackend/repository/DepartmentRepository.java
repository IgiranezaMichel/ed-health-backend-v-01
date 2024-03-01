package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
