package com.edhealthbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.CertifiedStudent;
import com.edhealthbackend.model.Student;
public interface CertifiedStudentRepository extends JpaRepository<CertifiedStudent,Long>{
    Page<CertifiedStudent> findAllByStudent(Student student, PageRequest of);
    Page<CertifiedStudent> findAllByCertificate(Certificate certificate, PageRequest of);
    CertifiedStudent findByCertificateAndStudent(Certificate certificate, Student student);
}
