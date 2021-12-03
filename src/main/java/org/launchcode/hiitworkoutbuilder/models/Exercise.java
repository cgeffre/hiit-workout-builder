package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise extends AbstractEntity {

    @ManyToMany(mappedBy="exercises")
    @Valid
    private List<Workout> workouts = new ArrayList<>();

    public Exercise() {}

}
