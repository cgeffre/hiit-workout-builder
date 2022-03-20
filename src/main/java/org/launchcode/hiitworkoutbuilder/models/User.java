package org.launchcode.hiitworkoutbuilder.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @OneToMany
    @JoinColumn(name="user_id")
    @Cascade(CascadeType.ALL)
    private Set<Exercise> exercises = new HashSet<>();

    @OneToMany
    @JoinColumn(name="user_id")
    @Cascade(CascadeType.ALL)
    private Set<Workout> workouts = new HashSet<>();

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public int getId() {
        return id;
    }

    public String getPwHash() {
        return pwHash;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public Set<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Set<Workout> workouts) {
        this.workouts = workouts;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    public void updatePassword(String newPassword) {
        this.pwHash = encoder.encode(newPassword);
    }
}
