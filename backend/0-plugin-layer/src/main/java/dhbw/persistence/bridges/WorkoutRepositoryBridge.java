package dhbw.persistence.bridges;

import dhbw.entities.Workout;
import dhbw.persistence.repositories.SpringDataWorkoutRepository;
import dhbw.repositories.WorkoutRepository;
import dhbw.valueObjects.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutRepositoryBridge implements WorkoutRepository {

    private SpringDataWorkoutRepository springDataWorkoutRepository;

    @Autowired
    public WorkoutRepositoryBridge(SpringDataWorkoutRepository springDataWorkoutRepository) {
        this.springDataWorkoutRepository = springDataWorkoutRepository;
    }

    @Override
    public List<Workout> findAll() {
        return springDataWorkoutRepository.findAll();
    }

    @Override
    public Workout save(Workout newWorkout) {
        return springDataWorkoutRepository.save(newWorkout);
    }

    @Override
    public Workout getByName(Name name) {
        return springDataWorkoutRepository.getWorkoutByName(name);
    }
}
