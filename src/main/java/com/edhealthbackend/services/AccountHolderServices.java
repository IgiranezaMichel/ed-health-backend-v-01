package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.edhealthbackend.enums.Role;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.AccountHolderPage;
import com.edhealthbackend.repository.AccountHolderRepository;

@Service
public class AccountHolderServices extends DefaultRepositoryMethod<AccountHolder,Long>{
public AccountHolderServices(JpaRepository<AccountHolder, Long> jpaRepository) {
        super(jpaRepository);
    }

@Autowired private AccountHolderRepository userRepository;

@Override
public List<AccountHolder> search(String search) {
  return userRepository.findAll().stream().filter(u->(
    u.getDob().toString().toLowerCase().equalsIgnoreCase(search)
    ||u.getEmail().equalsIgnoreCase(search)
    ||u.getName().equalsIgnoreCase(search)
    ||u.getPhoneNumber().equalsIgnoreCase(search)
    ||u.getRole().name().equals(search)
  )).toList();
}

@Override
public String deleteById(Long id) {
    AccountHolder user=userRepository.findById(id).orElse(null);
    this.deleteById(id);
   return user.getName()+" Deleted successful";
}

public AccountHolderPage findAllUserByRole(com.edhealthbackend.enums.Role role, PaginationInput in) {
  Page<AccountHolder>page=userRepository.findAllByRole(role,PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
  return new AccountHolderPage(page.getContent(), page.getNumber(),page.getTotalPages(), page.getTotalElements());
}

public AccountHolder findByEmail(String email) {
 return userRepository.findByEmail(email);
}
public long getTotalAccountHolderByRole(Role role){
  return userRepository.countByRole(role);
}
}
