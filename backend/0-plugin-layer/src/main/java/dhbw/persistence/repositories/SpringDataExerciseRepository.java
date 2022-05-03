package dhbw.persistence.repositories;

import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.valueObjects.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataExerciseRepository extends JpaRepository<Exercise, String> {

    Exercise findExerciseByNameAndVariant(Name name, ExerciseVariant variant);
}
