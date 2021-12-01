package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "workout_id")
    private List<Exercise> exercises = new ArrayList<>();

    public Workout() {}

    public Workout(List someExercises) {
        super();
        this.exercises = someExercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

}
