package com.edhealthbackend.model.gql.InputDefs;
import java.util.List;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {
private List<T> content;
private  int pageNumber;
private int  totalPages;
private long size;
}
