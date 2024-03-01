package com.edhealthbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.model.HospitalAdmin;

public interface HospitalAdminRepository extends JpaRepository<HospitalAdmin,Long>{
    @Query("SELECT new com.edhealthbackend.DTO.BarchartDTO(COUNT(st.id),st.status) FROM HospitalAdmin st  GROUP BY st.status")
    List<BarchartDTO<HospitalAdmin>> findHospitalAdminStatisticsByAdminStatus();

}
