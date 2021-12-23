package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise extends AbstractEntity {

    @ManyToMany(mappedBy="exercises")
    @Valid
    private Set<Workout> workouts = new HashSet<>();

    public Exercise() {}

    @PreRemove
    public void removeExerciseFromWorkouts() {
        for (Workout workout : workouts) {
            workout.getExercises().remove(this);
        }
    }

}
