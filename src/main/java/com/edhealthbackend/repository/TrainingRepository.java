package com.edhealthbackend.repository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Training;
@Repository
public interface TrainingRepository extends JpaRepository<Training,Long>{
    long countByHospital(Hospital hospital);
    Page<Training> findAllByHospitalAndNcnmApprovalStatus(Hospital hospital, String ncnmApprovalStatus, PageRequest of);
    Page<Training> findAllByNcnmApprovalStatus(String ncnmApprovalStatus, PageRequest of);
    Page<Training> findAllByDeadlineBeforeAndNcnmApprovalStatus(LocalDateTime now, String ncnmApprovalStatus,
            PageRequest of);
    Page<Training> findAllByDeadlineAfterAndNcnmApprovalStatus(LocalDateTime now, String ncnmApprovalStatus,
            PageRequest of);
    Page<Training> findAllByNcnmApprovalStatusAndDeadlineBefore(String status, LocalDateTime now, PageRequest of);

}
