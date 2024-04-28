package com.edhealthbackend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.enums.Role;
import com.edhealthbackend.model.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder,Long>{
    Page<AccountHolder> findAllByRole(Role role, PageRequest of);
    AccountHolder findByEmail(String email);
    long countByRole(Role student);
    boolean existsByEmail(String email);

}
