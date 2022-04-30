package dhbw.repositories;

import dhbw.entities.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.valueObjects.Name;

import java.util.List;

public interface ExerciseRepository {

    Exercise getById(Name name, ExerciseVariant variant);

    Exercise save(Exercise newExercise);

    List<Exercise> findAll();
}
