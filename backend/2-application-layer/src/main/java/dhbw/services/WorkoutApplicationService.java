package dhbw.services;

import dhbw.entities.Workout;
import dhbw.repositories.WorkoutRepository;
import dhbw.valueObjects.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutApplicationService {

    private WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutApplicationService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout getByName(Name name) {
        return workoutRepository.getByName(name);
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

}
