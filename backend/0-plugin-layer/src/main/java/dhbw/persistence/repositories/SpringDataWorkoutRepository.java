package dhbw.persistence.repositories;

import dhbw.entities.Workout;
import dhbw.valueObjects.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataWorkoutRepository extends JpaRepository<Workout, String> {
    Workout getWorkoutByName(Name name);
}
