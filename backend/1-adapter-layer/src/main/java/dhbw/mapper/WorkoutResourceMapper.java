package dhbw.mapper;

import dhbw.entities.Workout;
import dhbw.resources.WorkoutResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WorkoutResourceMapper implements Function<Workout, WorkoutResource> {

    @Override
    public WorkoutResource apply(Workout workout) {
        return map(workout);
    }

    private WorkoutResource map(Workout workout) {
        return new WorkoutResource(workout.getName(), workout.getDescription(), workout.getExercises());
    }
}
