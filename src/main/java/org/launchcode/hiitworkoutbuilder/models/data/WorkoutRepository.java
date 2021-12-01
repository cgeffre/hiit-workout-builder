package org.launchcode.hiitworkoutbuilder.models.data;

import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Integer> {
}
