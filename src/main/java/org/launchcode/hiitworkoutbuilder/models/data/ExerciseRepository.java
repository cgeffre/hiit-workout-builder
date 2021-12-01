package org.launchcode.hiitworkoutbuilder.models.data;

import org.launchcode.hiitworkoutbuilder.models.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
}