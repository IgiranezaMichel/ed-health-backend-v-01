package com.edhealthbackend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.edhealthbackend.enums.Role;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.model.gql.pagination.AccountHolderPage;
import com.edhealthbackend.services.AccountHolderServices;

@Controller
public class UserController {
@Autowired private AccountHolderServices userServices;
@QueryMapping
public AccountHolder findIUserById(@Argument(name= "id")long id){
    return userServices.findById(id);
}
@MutationMapping
public AccountHolder registerUser(@Argument(name = "input")AccountHolderInput in){
return userServices.saveOrUpdate(new AccountHolder(in.getId(),in.getName(), in.getGender(), in.getEmail(), in.getPhoneNumber(), in.getProfilePicture(), in.getDob(), in.getPassword(), in.getRole(), null, null,null,null));
}
@QueryMapping
public AccountHolderPage getAllUserByRole(@Argument(name = "role")Role role,@Argument(name = "input")PaginationInput in){
return userServices.findAllUserByRole(role,in);
}
@QueryMapping
public long getTotalAccountHolderByRole(@Argument(name = "role")Role role){
    return userServices.getTotalAccountHolderByRole(role);
  }
  
}
