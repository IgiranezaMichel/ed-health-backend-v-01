package com.edhealthbackend.model.gql.pagination;

import java.util.List;

import com.edhealthbackend.model.JobApplication;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class JobApplicationPage extends Pagination<JobApplication> {
    public JobApplicationPage(List<JobApplication> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}