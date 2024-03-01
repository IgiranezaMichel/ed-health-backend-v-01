package com.edhealthbackend.interfaces;

import java.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.edhealthbackend.model.gql.InputDefs.Pagination;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;

public abstract class DefaultRepositoryMethod<T, K>{
    private JpaRepository<T, K> jpaRepository;
    public DefaultRepositoryMethod(JpaRepository<T, K> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public abstract List<T> search(String search);
    public abstract  String deleteById(K id);

    public T saveOrUpdate(T data) {
        return jpaRepository.save(data);
    }

    public List<T> getAll(String sort) {
        return jpaRepository.findAll(Sort.by(sort));
    }

    public Pagination<T> pagination(PaginationInput input) {
        org.springframework.data.domain.Page<T> page = jpaRepository
                .findAll(PageRequest.of(input.getPageNumber(), input.getPageSize(), Sort.by(input.getSort())));
        return new Pagination<T>(page.getContent(), page.getNumber(), page.getTotalPages(), this.size());
    }

    public long size() {
        return jpaRepository.count();
    }

    public T findById(K id) {
        return jpaRepository.findById(id).orElse(null);
    }
}
