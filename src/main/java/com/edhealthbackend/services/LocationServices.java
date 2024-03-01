package com.edhealthbackend.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edhealthbackend.model.Location;
import com.edhealthbackend.repository.LocationRepository;
@Service
public class LocationServices {
@Autowired private LocationRepository locationRepository;
public Location saveOrUpdateLocation(Location location){
   return locationRepository.save(location);
}
public Location findLocationById(long id){
    return locationRepository.findById(id).orElse(null);
}
public String deleteLocationById(long id){
try {
    Location location=this.findLocationById(id);
    if(location==null) throw new Exception("Location not found");
    else{
        locationRepository.deleteById(id);
        return location.getName()+" Deleted successful";
    }
} catch (Exception e) {
   return e.getMessage();
}
}
public java.util.List<Location> searchLocation(String search) {
   return locationRepository.findAll();
}
public java.util.List<Location> getAllLocation(String sortBy) {
 return locationRepository.findAll(Sort.by(sortBy));
}
public List<Location> filterLocationByType(String sort, String type) {
  return locationRepository.findAllByType(Sort.by(sort),type);
}
}
