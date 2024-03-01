package com.edhealthbackend.model.gql.pagination;

import java.util.List;

import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class HospitalPage extends Pagination<Hospital>{
    public HospitalPage(List<Hospital> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }

}
