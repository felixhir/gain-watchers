package dhbw.repositories;

import dhbw.entities.Workout;

import java.util.List;

public interface WorkoutRepository {

    List<Workout> findAll();

    Workout save(Workout newWorkout);

    Workout getByName(String name);

}
