package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edhealthbackend.model.Certificate;
public interface CertificateRepository extends JpaRepository<Certificate,Long>{
}
