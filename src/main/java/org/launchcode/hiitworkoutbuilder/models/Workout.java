package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

@Entity
public class Workout extends AbstractEntity {

    @ManyToMany
    @Valid
    private List<Exercise> exercises = new ArrayList<>();

    @Min(value=1, message="Number must be positive")
    @Max(value=99, message="Number must be less than 100 seconds")
    private int secondsDuration;

    @Min(value=0, message="Number must be zero or greater")
    private int restInterval;

    public Workout() {}

    public Workout(List someExercises, int someDuration, int someInterval) {
        super();
        this.exercises = someExercises;
        this.secondsDuration = someDuration;
        this.restInterval = someInterval;
    }

    public ArrayList<Exercise> exerciseRandomizer(Workout aWorkout) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.addAll(aWorkout.getExercises());
        Collections.shuffle(exercises);
        return exercises;
    }

    public ArrayList<Exercise> addRestsToWorkout(ArrayList<Exercise> exerciseList, Workout workout, ArrayList<Exercise> exercises) {
        for (int i = 0; i < exerciseList.size(); i++) {
            if ((i > 0) && (i % workout.getRestInterval() == 0) && (i < exerciseList.size())) {
                Exercise rest = new Exercise();
                rest.setName("Rest");
                exercises.add(rest);
            }
            exercises.add(exerciseList.get(i));
        }
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

    public int getRestInterval() {
        return restInterval;
    }

    public void setRestInterval(int restInterval) {
        this.restInterval = restInterval;
    }

}
