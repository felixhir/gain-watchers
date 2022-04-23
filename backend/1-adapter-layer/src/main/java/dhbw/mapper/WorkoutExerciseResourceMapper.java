package dhbw.mapper;

import dhbw.entities.Exercise;
import dhbw.entities.ExerciseType;
import dhbw.entities.ExerciseVariant;
import dhbw.entities.WorkoutExercise;
import dhbw.resources.WorkoutExerciseResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WorkoutExerciseResourceMapper implements Function<WorkoutExercise, WorkoutExerciseResource> {

    @Override
    public WorkoutExerciseResource apply(WorkoutExercise workoutExercise) {
        return map(workoutExercise);
    }

    private WorkoutExerciseResource map(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseResource(workoutExercise.getExercise().getName(), workoutExercise.getExercise().getVariant().toString(), workoutExercise.getReps(), workoutExercise.getSets());
    }

    public WorkoutExercise reverse(WorkoutExerciseResource workoutExerciseResource, Exercise exercise) {
        return new WorkoutExercise(exercise, workoutExerciseResource.getSets(), workoutExerciseResource.getReps());
    }
}
