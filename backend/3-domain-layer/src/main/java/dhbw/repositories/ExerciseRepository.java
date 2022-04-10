package dhbw.repositories;

import dhbw.entities.Exercise;

import java.util.List;

public interface ExerciseRepository {

    Exercise getByName(String name);

    Exercise save(Exercise newExercise);

    List<Exercise> findAll();
}
