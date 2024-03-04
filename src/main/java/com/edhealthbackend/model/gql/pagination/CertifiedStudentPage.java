package com.edhealthbackend.model.gql.pagination;

import java.util.List;

import com.edhealthbackend.model.CertifiedStudent;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class CertifiedStudentPage  extends Pagination<CertifiedStudent>{
    public CertifiedStudentPage(List<CertifiedStudent> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
