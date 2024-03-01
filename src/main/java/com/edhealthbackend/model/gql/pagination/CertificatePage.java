package com.edhealthbackend.model.gql.pagination;

import java.util.List;

import com.edhealthbackend.model.Certificate;
import com.edhealthbackend.model.gql.InputDefs.Pagination;

public class CertificatePage extends Pagination<Certificate>{
    public CertificatePage(List<Certificate> content, int pageNumber, int pageSize, long size) {
        super(content, pageNumber, pageSize, size);
    }
}
