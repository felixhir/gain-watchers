package dhbw.repositories;

import dhbw.entities.Exercise;

public interface ExerciseRepository {

    Exercise getByName(String name);

    void create(Exercise newExercise);
}
