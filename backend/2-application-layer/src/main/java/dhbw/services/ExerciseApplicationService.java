package dhbw.services;

import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.repositories.ExerciseRepository;
import dhbw.valueObjects.Name;
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

    public Exercise getById(Name name, ExerciseVariant variant) {
        return exerciseRepository.getById(name, variant);
    }
}
