package dhbw.mapper;

import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.resources.ExerciseResource;
import dhbw.valueObjects.Name;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExerciseResourceMapper implements Function<Exercise, ExerciseResource> {

    @Override
    public ExerciseResource apply(Exercise exercise) {
        return map(exercise);
    }

    private ExerciseResource map(Exercise exercise) {
        return new ExerciseResource(exercise.getName(), exercise.getVariant().getName());
    }

    public Exercise reverse(ExerciseResource newExercise) {
        return new Exercise(new Name(newExercise.getName()), ExerciseVariant.valueOf(newExercise.getExerciseVariant()));
    }
}
