package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Workout extends AbstractEntity {

    @ManyToMany
    @Valid
    private List<Exercise> exercises = new ArrayList<>();

    @Min(value=1, message="Number must be positive")
    private int secondsDuration;

    public Workout() {}

    public Workout(List someExercises, int someDuration) {
        super();
        this.exercises = someExercises;
        this.secondsDuration = someDuration;
    }

    public ArrayList<Exercise> exerciseRandomizer(Workout aWorkout) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.addAll(aWorkout.getExercises());
        Collections.shuffle(exercises);
        return exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getSecondsDuration() {
        return secondsDuration;
    }

    public void setSecondsDuration(int secondsDuration) {
        this.secondsDuration = secondsDuration;
    }

}
