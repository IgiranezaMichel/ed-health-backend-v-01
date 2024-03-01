package com.edhealthbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
