package com.edhealthbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.enums.StudentStatus;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.Student;
public interface StudentRepository extends JpaRepository<Student,Long>{

    Page<Student> findAllBySchool(School school, PageRequest of);

    Page<Student> findAllBySchoolAndStatus(School school, StudentStatus status, PageRequest of);
     @Query("SELECT new com.edhealthbackend.DTO.BarchartDTO(COUNT(st.id),st.status) FROM Student st WHERE st.school = :school GROUP BY st.status")
    List<BarchartDTO<StudentStatus>> studentStatisticsByStatus(School school);
    @Query("SELECT new com.edhealthbackend.DTO.BarchartDTO(COUNT(st.id),st.status) FROM Student st GROUP BY st.status")
    List<BarchartDTO<StudentStatus>> studentStatisticsByStatus();

}
