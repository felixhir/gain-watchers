package dhbw.mapper;

import dhbw.entities.Exercise;
import dhbw.entities.ExerciseType;
import dhbw.entities.ExerciseVariant;
import dhbw.resources.ExerciseResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseResourceMapper implements Function<Exercise, ExerciseResource> {

    @Override
    public ExerciseResource apply(Exercise exercise) {
        return map(exercise);
    }

    private ExerciseResource map(Exercise exercise) {
        return new ExerciseResource(exercise.getName(), exercise.getType().getName(), exercise.getVariant().getName());
    }

    public Exercise reverse(ExerciseResource newExercise) {
        return new Exercise(newExercise.getName(), ExerciseType.valueOf(newExercise.getExerciseType()), ExerciseVariant.valueOf(newExercise.getExerciseVariant()));
    }
}
