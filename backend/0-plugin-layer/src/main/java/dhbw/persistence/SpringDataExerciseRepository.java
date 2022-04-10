package dhbw.persistence;

import dhbw.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataExerciseRepository extends JpaRepository<Exercise, String> {
}
