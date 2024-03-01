package com.edhealthbackend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.edhealthbackend.enums.StudentStatus;
import com.edhealthbackend.interfaces.DefaultRepositoryMethod;
import com.edhealthbackend.model.AnnualAcademicResult;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.AnnualAcademicResultInput;

@Service
public class AnnualAcademicReportServices extends DefaultRepositoryMethod<AnnualAcademicResult, Long> {
    @Autowired
    private StudentServices studentServices;

    public AnnualAcademicReportServices(JpaRepository<AnnualAcademicResult, Long> jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public List<AnnualAcademicResult> search(String search) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public String deleteById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    public String saveAcademicReport(AnnualAcademicResultInput in) {
        Student student = studentServices.findById(in.getStudentId());
        student.setStatus(StudentStatus.COMPLETE);
        studentServices.saveOrUpdate(student);
        return this.saveOrUpdate(new AnnualAcademicResult(in.getId(), in.getTotalMarks(), in.getDisciplineMarks(),
                student, LocalDateTime.now())).getStudent().getUser().getName() + " Added successful";
    }

}
