package dhbw.persistence.repositories;

import dhbw.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataWorkoutRepository extends JpaRepository<Workout, String> {

}
