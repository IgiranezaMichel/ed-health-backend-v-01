package com.edhealthbackend.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.Training;
import com.edhealthbackend.model.gql.InputDefs.PaginationInput;
import com.edhealthbackend.model.gql.InputDefs.TrainingInput;
import com.edhealthbackend.model.gql.pagination.TrainingPage;
import com.edhealthbackend.services.HospitalServices;
import com.edhealthbackend.services.LocationServices;
import com.edhealthbackend.services.TrainingServices;

@Controller
public class TrainingController {
    @Autowired
    private TrainingServices trainingServices;
    @Autowired
    private LocationServices locationServices;
    @Autowired
    private HospitalServices hospitalServices;
    @MutationMapping
    public Training registerTraining(@Argument(name = "input") TrainingInput training) {
        training.setLocation(locationServices.findLocationById(training.getLocationId()));
        training.setHospital(hospitalServices.findHospitalById(training.getHospitalId()));
        return trainingServices.saveOrUpdateTraining(new Training(training.getId(), training.getTitle(), training.getDescription(), training.getDeadline(), training.getNcnmApprovalStatus(), training.getLocation(), training.getHospital()));
    }

    @MutationMapping
    public String deleteTrainingById(@Argument(name = "id") long id) {
        return trainingServices.deleteTrainingById(id);
    }
    @MutationMapping
    public ResponseEntity<String> updateTrainingStatus(@Argument(name = "trainingId")long trainingId,@Argument(name = "status")String status){
        return trainingServices.updateTrainingStatus(trainingId,status);
    }
    @QueryMapping
    public Training findTrainingById(@Argument("id")long id) {
        if(id==0){
            return null;
        }
        return trainingServices.findTrainingById(id);
    }
    @QueryMapping
    public List<Training> getAllTraining(@Argument("sort") String sort) {
        return trainingServices.getAllTraining(sort);
    }
    @QueryMapping
    public List<Training> searchTraining(@Argument("search") String search) {
        return trainingServices.searchTraining(search);
    }

    @QueryMapping
    public TrainingPage trainingPagination(@Argument("input") PaginationInput input) {
        return trainingServices.trainingPage(input.getPageNumber(), input.getPageSize(), input.getSort());
    }
}
