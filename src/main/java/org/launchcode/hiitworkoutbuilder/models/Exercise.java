package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise extends AbstractEntity {

    @ManyToMany(mappedBy="exercises")
    @Valid
    private Set<Workout> workouts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Exercise() {}

    @PreRemove
    public void removeExerciseFromWorkouts() {
        for (Workout workout : workouts) {
            workout.getExercises().remove(this);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
