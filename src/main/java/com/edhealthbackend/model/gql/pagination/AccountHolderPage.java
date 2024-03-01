package com.edhealthbackend.model.gql.pagination;

import java.util.List;
import com.edhealthbackend.model.AccountHolder;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class AccountHolderPage extends Pagination<AccountHolder>{
  public AccountHolderPage(List<AccountHolder> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
