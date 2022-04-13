package dhbw.services;

import dhbw.entities.Exercise;
import dhbw.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseApplicationService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseApplicationService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise getByName(String name) {
        return exerciseRepository.getByName(name);
    }
}