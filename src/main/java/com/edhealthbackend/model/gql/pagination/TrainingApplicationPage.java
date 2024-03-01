package com.edhealthbackend.model.gql.pagination;

import java.util.List;
import com.edhealthbackend.model.TrainingApplication;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class TrainingApplicationPage extends Pagination<TrainingApplication>{
  public TrainingApplicationPage(List<TrainingApplication> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
