package dhbw.persistence.bridges;

import dhbw.entities.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.persistence.repositories.SpringDataExerciseRepository;
import dhbw.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseRepositoryBridge implements ExerciseRepository {

    private SpringDataExerciseRepository springDataExerciseRepository;

    @Autowired
    public ExerciseRepositoryBridge(SpringDataExerciseRepository springDataExerciseRepository) {
        this.springDataExerciseRepository = springDataExerciseRepository;
    }

    @Override
    public Exercise getById(String name, ExerciseVariant variant) {
        return springDataExerciseRepository.findExerciseByNameAndVariant(name, variant);
    }

    @Override
    public Exercise save(Exercise newExercise) {
        return springDataExerciseRepository.save(newExercise);
    }

    @Override
    public List<Exercise> findAll() {
        return springDataExerciseRepository.findAll();
    }
}
