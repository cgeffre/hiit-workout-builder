package org.launchcode.hiitworkoutbuilder.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Exercise extends AbstractEntity {

    @ManyToOne
    private Workout workout;

    public Exercise() {}

}
