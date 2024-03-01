package com.edhealthbackend.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String type;
    @ManyToOne(targetEntity =Location.class,cascade = CascadeType.ALL)
    private Location location;
    public Location(long id,String name,String type,Location location){
        this.id=id;
        this.name=name;
        this.type=type;
        this.location=location;
    }
    @OneToMany(mappedBy = "location",targetEntity = Location.class,cascade = CascadeType.ALL)
    private List<Location> locationList;
    @OneToMany(mappedBy = "location",targetEntity = Hospital.class,cascade = CascadeType.ALL)
    private List<Hospital>hospitalList;
    @OneToMany(mappedBy = "location",targetEntity = Training.class,cascade = CascadeType.ALL)
    private List<Training>trainingList;
    @OneToMany(mappedBy = "location",targetEntity = School.class,cascade = CascadeType.ALL)
    private List<School>shoolList;
}
