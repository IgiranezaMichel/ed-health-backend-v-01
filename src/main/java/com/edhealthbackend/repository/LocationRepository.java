package com.edhealthbackend.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edhealthbackend.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{

    List<Location> findAllByType(Sort by, String type);

}
