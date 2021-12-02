package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise extends AbstractEntity {

    @ManyToMany(mappedBy="exercises")
    private List<Workout> workouts = new ArrayList<>();

    public Exercise() {}

}
