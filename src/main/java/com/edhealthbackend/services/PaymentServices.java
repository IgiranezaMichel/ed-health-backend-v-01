package com.edhealthbackend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Payment;
import com.edhealthbackend.repository.PaymentRepository;
@Service
public class PaymentServices extends DefaultRepositoryMethod<Payment,Long>{
public PaymentServices(JpaRepository<Payment, Long> jpaRepository) {
        super(jpaRepository);
    }

@Autowired private PaymentRepository paymentRepository;
    @Override
    public String deleteById(Long id) {
      try {
      Payment payment=this.findById(id);
      paymentRepository.delete(payment);
      return "Payment saved successful";
      } catch (Exception e) {
        return "Certificate not found";
      }
    }
    @Override
    public List<Payment> search(String search) {
     String searchString=search.toLowerCase();
      return paymentRepository.findAll().stream()
      .filter(p->(p.getPaymentCode().equals(searchString))
      ||p.getTimeStamp().toString().toLowerCase().equals(searchString))
      .toList();
    }



}
