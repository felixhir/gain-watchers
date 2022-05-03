package dhbw.mapper;

import dhbw.entities.*;
import dhbw.resources.WorkoutResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WorkoutResourceMapper implements Function<Workout, WorkoutResource> {

    @Override
    public WorkoutResource apply(Workout workout) {
        return map(workout);
    }

    private WorkoutResource map(Workout workout) {
        return new WorkoutResource(workout.getName(), workout.getDescription(), workout.getDays().getValue(),
                workout.getExercises().stream().map(new WorkoutExerciseResourceMapper()).collect(Collectors.toList()));
    }
}
