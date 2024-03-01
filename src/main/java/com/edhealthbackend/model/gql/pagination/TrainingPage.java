package com.edhealthbackend.model.gql.pagination;

import java.util.List;

import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.Pagination;
public class TrainingPage extends Pagination<Training>{
    public TrainingPage(List<Training>content,int pageNumber,int pageSize,long l){
        super(content, pageNumber, pageSize, l);
    }
}
