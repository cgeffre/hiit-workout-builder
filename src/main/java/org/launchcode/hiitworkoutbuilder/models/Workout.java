package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Workout extends AbstractEntity {

    @NotBlank(message="Name is required")
    @Size(max=50, message="Name must be less than 50 characters")
    private String name;

    @ManyToMany
    @Valid
    private Set<Exercise> exercises = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Min(value=1, message="Number must be positive")
    @Max(value=99, message="Number must be less than 100 seconds")
    private int secondsDuration;

    @Min(value=0, message="Number must be zero or greater")
    private int restInterval;

    public Workout() {}

    public Workout(String aName, Set someExercises, int someDuration, int someInterval) {
        super();
        this.name = aName;
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

    public int totalDurationOfWorkout(ArrayList<Exercise> exercises, Workout aWorkout) {
        int numberOfExercises = exercises.size();
        int secondsDuration = aWorkout.getSecondsDuration();
        return Math.round((numberOfExercises*secondsDuration)/60);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
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

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
