package com.edhealthbackend.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.edhealthbackend.model.Hospital;
import com.edhealthbackend.model.Location;
import com.edhealthbackend.model.gql.InputDefs.HospitalInput;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.pagination.HospitalPage;
import com.edhealthbackend.model.gql.pagination.TrainingPage;
import com.edhealthbackend.services.HospitalServices;
import com.edhealthbackend.services.LocationServices;

@Controller
public class HospitalController {
    @Autowired
    private HospitalServices hospitalServices;
    @Autowired
    private LocationServices locationServices;

    @MutationMapping
    public ResponseEntity<String> registerHospital(@Argument(name = "input") HospitalInput hospitalInput) {
        Location location = locationServices.findLocationById(hospitalInput.getLocationId());
        Hospital hospital = hospitalServices.saveOrUpdateHospital(new Hospital(hospitalInput.getId(),
                hospitalInput.getName(), hospitalInput.getLogo(), hospitalInput.getDescription(), LocalDateTime.now(),
                location));
        return new ResponseEntity<>(hospital.getName() + " saved successful", HttpStatus.OK);
    }

    @MutationMapping
    public ResponseEntity<String> deleteHospitalById(@Argument(name = "id") long id) {
        try {
            String response = hospitalServices.deleteById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @QueryMapping
    public Hospital findHospitalById(@Argument(name = "id") long id) {
        return hospitalServices.findHospitalById(id);
    }

    @QueryMapping
    public TrainingPage findListOfTrainingByHospitalIdAndNcnmApprovalStatus(@Argument("hospitalId") long hospitalId,
            @Argument("ncnmApprovalStatus") String ncnmApprovalStatus, @Argument("input") PaginationInput input) {
        return hospitalServices.findListOfTrainingByHospitalIdAndNcnmApprovalStatus(hospitalId, ncnmApprovalStatus,
                input);
    }

    @QueryMapping
    public TrainingPage findListOfNcnmApprovalStatusBeforeDeadline(
            @Argument("ncnmApprovalStatus") String ncnmApprovalStatus, @Argument("input") PaginationInput input) {
        return hospitalServices.findListOfNcnmApprovalStatusBeforeDeadLine(ncnmApprovalStatus, input);
    }

    @QueryMapping
    public TrainingPage findListOfNcnmApprovalStatusAfterDeadline(
            @Argument("ncnmApprovalStatus") String ncnmApprovalStatus, @Argument("input") PaginationInput input) {
        return hospitalServices.findListOfNcnmApprovalStatusAfterDeadLine(ncnmApprovalStatus, input);
    }

    @QueryMapping
    public HospitalPage getAllHospital(@Argument(name = "input") PaginationInput in) {
        return hospitalServices.hospitalPagination(in);
    }

    @QueryMapping
    public List<Hospital> searchHospital(@Argument(name = "search") String search) {
        return hospitalServices.searchHospital(search);
    }

    @QueryMapping
    public HospitalPage hospitalPagination(@Argument(name = "input") PaginationInput input) {
        return hospitalServices.hospitalPagination(input);
    }

    @QueryMapping
    public long totalHospital() {
        return hospitalServices.totalHospital();
    }
}
