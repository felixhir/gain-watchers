package dhbw.persistence.repositories;

import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataExerciseRepository extends JpaRepository<Exercise, String> {

    Exercise findExerciseByNameAndVariant(String name, ExerciseVariant variant);
}
