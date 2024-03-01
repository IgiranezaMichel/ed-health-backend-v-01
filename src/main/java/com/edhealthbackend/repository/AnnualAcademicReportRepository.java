package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edhealthbackend.model.AnnualAcademicResult;
public interface AnnualAcademicReportRepository extends JpaRepository<AnnualAcademicResult,Long>{
}
