package dhbw.repositories;

import dhbw.entities.Workout;
import dhbw.valueObjects.Name;

import java.util.List;

public interface WorkoutRepository {

    List<Workout> findAll();

    Workout save(Workout newWorkout);

    Workout getByName(Name name);

}
