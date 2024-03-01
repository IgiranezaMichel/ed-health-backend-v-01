package com.edhealthbackend.model.gql.InputDefs;

import lombok.Data;

@Data
public class PaginationInput {
    private int pageNumber;
    private int pageSize;
    private String sort;
}
