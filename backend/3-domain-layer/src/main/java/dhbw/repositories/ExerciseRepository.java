package dhbw.repositories;

import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;

import java.util.List;

public interface ExerciseRepository {

    Exercise getById(String name, ExerciseVariant variant);

    Exercise save(Exercise newExercise);

    List<Exercise> findAll();
}
