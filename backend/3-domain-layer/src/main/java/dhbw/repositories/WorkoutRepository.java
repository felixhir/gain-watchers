package dhbw.repositories;

import dhbw.entities.Workout;

import java.util.List;

public interface WorkoutRepository {

    List<Workout> getAll();

    void create(Workout newWorkout);

    Workout getByName(String name);
}
