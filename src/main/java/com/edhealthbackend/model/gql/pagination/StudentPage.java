package com.edhealthbackend.model.gql.pagination;

import java.util.List;
import com.edhealthbackend.model.Student;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class StudentPage extends Pagination<Student>{
 public StudentPage(List<Student> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
