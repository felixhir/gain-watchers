package dhbw.persistence.repositories;

import dhbw.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataExerciseRepository extends JpaRepository<Exercise, String> {
}