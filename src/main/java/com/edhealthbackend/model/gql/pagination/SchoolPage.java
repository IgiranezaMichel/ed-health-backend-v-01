package com.edhealthbackend.model.gql.pagination;

import java.util.List;
import com.edhealthbackend.model.School;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class SchoolPage extends Pagination<School>{
 public SchoolPage(List<School> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
