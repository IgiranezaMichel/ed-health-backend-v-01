package com.edhealthbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.edhealthbackend.model.Location;
import com.edhealthbackend.model.gql.InputDefs.LocationInput;
import com.edhealthbackend.services.LocationServices;
import java.util.*;

@Controller
public class LocationController {
    @Autowired
    private LocationServices locationServices;

    @MutationMapping()
    public ResponseEntity<String> registerLocation(@Argument(name = "input") LocationInput locationInput) {
        Location Location = locationServices
                .saveOrUpdateLocation(new Location(locationInput.getId(), locationInput.getName(),
                        locationInput.getType(), locationServices.findLocationById(locationInput.getLocationId())));
        return new ResponseEntity<>(Location.getName() + " saved successful", HttpStatus.OK);
    }

    @MutationMapping
    public ResponseEntity<String> deleteLocationById(@Argument(name = "id") long id) {
        try {
            String response = locationServices.deleteLocationById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @QueryMapping
    public Location findLocationById(@Argument(name = "id") long id) {
        return locationServices.findLocationById(id);
    }

    @QueryMapping
    public List<Location> getAllLocation(@Argument(name = "sortBy") String sortBy) {
        return locationServices.getAllLocation(sortBy);
    }

    @QueryMapping
    public List<Location> searchLocation(@Argument(name = "search") String search) {
        return locationServices.searchLocation(search);
    }
    @QueryMapping()
    public List<Location> filterLocationByType(@Argument(name = "sort") String sort,@Argument(name = "type") String type){
        return locationServices.filterLocationByType(sort,type);
    }
}
