package com.edhealthbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Department;
import com.edhealthbackend.repository.DepartmentRepository;

@Service
public class DepartmentServices extends DefaultRepositoryMethod<Department, Long> {
  public DepartmentServices(JpaRepository<Department, Long> jpaRepository) {
    super(jpaRepository);
  }

  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public String deleteById(Long id) {
    try {
      Department department = this.findById(id);
      departmentRepository.delete(department);
      return department.getName() + " Removed successfully";
    } catch (Exception e) {
      return "Department not found";
    }
  }

  @Override
  public List<Department> search(String search) {
    String searchString = search.toLowerCase();
    return departmentRepository.findAll().stream().filter(f -> (f.getName().toLowerCase().equals(searchString)
        || f.getFaculty().getName().toLowerCase().equals(searchString))).toList();
  }

}
