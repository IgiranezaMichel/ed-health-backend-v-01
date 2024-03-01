package com.edhealthbackend.model.gql.pagination;

import java.util.List;
import com.edhealthbackend.model.Job;
import com.edhealthbackend.model.gql.InputDefs.Pagination;
public class JobPage extends Pagination<Job>{
  public JobPage(List<Job> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }

}
