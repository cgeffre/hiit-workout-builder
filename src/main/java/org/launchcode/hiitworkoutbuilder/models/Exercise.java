package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise extends AbstractEntity {

    @NotBlank(message="Name is required")
    @Size(max=50, message="Name must be less than 50 characters")
    private String name;

    @ManyToMany(mappedBy="exercises")
    @Valid
    private Set<Workout> workouts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise() {}

    @PreRemove
    public void removeExerciseFromWorkouts() {
        for (Workout workout : workouts) {
            workout.getExercises().remove(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
