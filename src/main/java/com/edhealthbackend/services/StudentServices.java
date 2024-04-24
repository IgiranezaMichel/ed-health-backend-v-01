package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;

import com.edhealthbackend.DTO.BarchartDTO;
import com.edhealthbackend.enums.StudentStatus;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.Department;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.InputDefs.StudentInput;
import com.edhealthbackend.model.gql.InputDefs.AccountHolderInput;
import com.edhealthbackend.model.gql.pagination.StudentPage;
import com.edhealthbackend.repository.SchoolRepository;
import com.edhealthbackend.repository.StudentRepository;

@Service
public class StudentServices extends DefaultRepositoryMethod<Student, Long> {
    public StudentServices(JpaRepository<Student, Long> jpaRepository) {
        super(jpaRepository);
    }

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private AccountHolderServices userServices;
    @Autowired
    private SchoolServices schoolServices;
    @Autowired
    private DepartmentServices departmentServices;

    @Override
    public List<Student> search(String search) {
        return studentRepository.findAll().stream()
                .filter(student -> (student.getStatus().name().equalsIgnoreCase(search) ||
                        student.getEndingDate().toString().equalsIgnoreCase(search)
                        || student.getUser().getName().equalsIgnoreCase(search)
                        || student.getUser().getEmail().equalsIgnoreCase(search)
                        || student.getUser().getGender().equalsIgnoreCase(search)
                        || student.getUser().getPhoneNumber().equalsIgnoreCase(search)
                        || student.getUser().getDob().toString().equalsIgnoreCase(search)))
                .toList();
    }

    @Override
    public String deleteById(Long id) {
        Student student = this.findById(id);
        if (student == null)
            return "User not found";
        else {
            studentRepository.deleteById(id);
            return student.getUser().getName() + "Deleted successfully";
        }
    }

    public StudentPage studentPage(PaginationInput in) {
        Page<Student> page = studentRepository
                .findAll(PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
        return new StudentPage(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    public StudentPage studentListPage(long schoolId, PaginationInput in) {
        School school = schoolRepository.findById(schoolId).orElse(null);
        Page<Student> studentPage = studentRepository.findAllBySchool(school,
                PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
        return new StudentPage(studentPage.getContent(), studentPage.getNumber(), studentPage.getTotalPages(),
                studentPage.getNumberOfElements());
    }

    public StudentPage findStudentFromSameSchoolByStatusListPage(long schoolId, StudentStatus status,
            PaginationInput in) {
        School school = schoolRepository.findById(schoolId).orElse(null);
        Page<Student> studentPage = studentRepository.findAllBySchoolAndStatus(school, status,
                PageRequest.of(in.getPageNumber(), in.getPageSize(), Sort.by(in.getSort())));
        return new StudentPage(studentPage.getContent(), studentPage.getNumber(), studentPage.getTotalPages(),
                studentPage.getNumberOfElements());
    }

    public String saveOrUpdateStudent(StudentInput in) {
        try {
            AccountHolder user = userServices.findById(in.getUserId());
            School school = schoolServices.findById(in.getSchoolId());
            Department department = departmentServices.findById(in.getDepartmentId());
            if (user == null || school == null || department == null)
                throw new Exception("Error happen");
            return studentRepository.save(new Student(in.getId(), school, user, department, LocalDateTime.now(), null,
                    in.getStatus(), null, null)).getUser().getName() + "saved successful";
        } catch (Exception e) {
            return "User doesn't exist";
        }
    }

    @QueryMapping
    public List<BarchartDTO<StudentStatus>> studentStatus(long SchoolId) {
        School school = schoolRepository.findById(SchoolId).orElse(null);
        return studentRepository.studentStatisticsByStatus(school);
    }

    public String saveOrUpdateStudent(StudentInput in, AccountHolderInput user) {
        try {
            AccountHolder user1 = userServices.findByEmail(user.getEmail());
            if (user1 == null)
                user1 = userServices.saveOrUpdate(new AccountHolder(user.getId(), user.getName(), user.getGender(),
                        user.getEmail(), user.getPhoneNumber(), user.getProfilePicture(), user.getDob(),
                        user.getPassword(), user.getRole(), null, null, null, null));
            School school = schoolServices.findById(in.getSchoolId());
            Department department = departmentServices.findById(in.getDepartmentId());
            if (user == null || school == null || department == null)
                throw new Exception("Error happen");
            return studentRepository.save(new Student(in.getId(), school, user1, department, LocalDateTime.now(), null,
                    in.getStatus(), null, null)).getUser().getName() + " saved successful";
        } catch (Exception e) {
            return "User doesn't exist";
        }
    }

    public List<BarchartDTO<StudentStatus>> studentStatisticsByStatus() {
        return studentRepository.studentStatisticsByStatus();
    }

    public Student findByAccountHolder(AccountHolder accountHolder) {
       return studentRepository.findByUser(accountHolder);
    }

}
